package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import model.Connexion;
import model.ModelView;
import model.Professeurs;


public class ServiceProfesseurs 
{
    Connexion connex = new Connexion();
    
    public String formatSha1(String mdp, Connection con)
    {
        String motDePasse = null;
        try
        {
            String requete = "select encode(digest('"+mdp+"', 'sha1'), 'hex') as motDePasse";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            while(rs.next())
            {
                motDePasse = rs.getString("motDePasse");
                break;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return motDePasse;
    }
    
    public Boolean checkLoginProfesseurs(Professeurs prof, Connection con)
    {
        Boolean check = false;
        try {
            String motDePasse = formatSha1(prof.getMot_de_passe(), con);
            PreparedStatement st = con.prepareStatement("select count(*) as compte from professeurs where username = ? and mot_de_passe = ?");
            st.setString(1, prof.getUsername());
            st.setString(2, motDePasse);
            ResultSet rs = st.executeQuery();
            while(rs.next())
            {
                int compte = rs.getInt("compte");
                if(compte == 1)
                {
                    check = true;
                }
                break;
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        return check;
    }
    
    public ModelView traitementVerifierLogin(Professeurs prof) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Boolean condition = checkLoginProfesseurs(prof,con);
        if(condition == true)
        {
            retour = new ModelView("accueil.jsp");
        }
        else
        {
            HashMap data = new HashMap();
            String erreur = "Accès refusée!";
            data.put("erreur", erreur);
            retour = new ModelView(data,"/erreur.jsp");
        }
        con.close();
        return retour;
    }
}
  