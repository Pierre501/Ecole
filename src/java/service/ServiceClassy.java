package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import model.Classy;
import model.Connexion;
import model.ModelView;
import model.ResultatFinal;
import model.ViewEtudiants;


public class ServiceClassy 
{
    Connexion connex = new Connexion();
    
    public ModelView traitementListeClasses(Classy classes) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Object[] tabObject = classes.getAllObject(con);
        Classy[] tabClasses = new Classy[tabObject.length];
        ViewEtudiants[] tabEtudiants = null;
        for(int i = 0; i < tabClasses.length; i++)
        {
            tabClasses[i] = (Classy) tabObject[i];
        }
        HashMap data = new HashMap();
        data.put("tabViewEtudiants", tabEtudiants);
        data.put("classes", tabClasses);
        retour = new ModelView(data, "recherche.jsp");
        con.close();
        return retour;
    }
    
    public ModelView traitementListeClasses2(Classy classes) throws SQLException
    {
        ModelView model = traitementListeClasses(classes);
        HashMap data = model.getData();
        ModelView retour = new ModelView(data, "/liste.jsp");
        return retour;
    }
    
    public ModelView traitementListeClasses3(Classy classes) throws SQLException
    {
        ModelView model = traitementListeClasses2(classes);
        HashMap data = model.getData();
        ModelView retour = new ModelView(data, "/ajouter.jsp");
        return retour;
    }
    
    public ModelView traitementListeClasses4(Classy classes) throws SQLException
    {
        ModelView model = traitementListeClasses2(classes);
        ResultatFinal[] resultatAdmis = null;
        ResultatFinal[] resultatNonAdmis = null;
        HashMap data = model.getData();
        data.put("resultatAdmis", resultatAdmis);
        data.put("resultatNonAdmis", resultatNonAdmis);
        ModelView retour = new ModelView(data, "/resultat.jsp");
        return retour;
    }
}
