package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import model.Classy;
import model.Connexion;
import model.ModelView;
import model.Notes;
import model.Semestres;
import model.ViewEtudiants;


public class ServiceSemestres 
{
    Connexion connex = new Connexion();
    
    public ModelView traitementListeSemestres(Semestres semestres) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Object[] tabObject = semestres.getAllObject(con);
        Semestres[] tabSemestres = new Semestres[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabSemestres[i] = (Semestres) tabObject[i];
        }
        ServiceClassy serviceClasses = new ServiceClassy();
        Classy classes = new Classy();
        Notes[] tabNotes = null;
        ModelView model = serviceClasses.traitementListeClasses(classes);
        Classy[] tabClasses = (Classy[]) model.getData().get("classes");
        ViewEtudiants etudiants = null;
        HashMap data = new HashMap();
        data.put("etudiant", etudiants);
        data.put("notes", tabNotes);
        data.put("classes", tabClasses);
        data.put("tabSemestres", tabSemestres);
        retour = new ModelView(data, "/bulletin.jsp");
        con.close();
        return retour;        
    }
}
