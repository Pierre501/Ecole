package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import model.Connexion;
import model.Directeur;
import model.ModelView;
import model.Validation_notes;


public class ServiceDirecteur 
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
    
    public Boolean verifierLoginDirecteur(Directeur directeur, Connection con) throws SQLException
    {
        Boolean verifier = false;
        String motDePasse = formatSha1(directeur.getMotDePasse(), con);
        PreparedStatement pstmt = con.prepareStatement("select count(*) compte from chefs_de_etablissements where username=? and mot_de_passe=?");
        pstmt.setString(1, directeur.getUsername());
        pstmt.setString(2, motDePasse);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next())
        {
            int compte = rs.getInt("compte");
            if(compte == 1)
            {
                verifier = true;
            }
        }
        return verifier;
    }
    
    public Validation_notes[] getAllValidationNotes(Connection con)
    {
        Validation_notes validation = new Validation_notes();
        Object[] tabObject = validation.getAllObject(con);
        Validation_notes[] tabValidation = new Validation_notes[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabValidation[i] = (Validation_notes) tabObject[i];
        }
        return tabValidation;
    }
    
    public ModelView traitementLoginDirecteur(Directeur directeur) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Boolean verifier = verifierLoginDirecteur(directeur, con);
        if(verifier == true)
        {
            Validation_notes[] tabValidation = getAllValidationNotes(con);
            HashMap data = new HashMap();
            data.put("tabValidation", tabValidation);
            retour = new ModelView(data, "/validation.jsp");
        }
        else
        {
            String erreur = "Accès refusée";
            HashMap data = new HashMap();
            data.put("erreur", erreur);
            retour = new ModelView(data, "/erreur2.jsp");
        }
        con.close();
        return retour;
    }
}
