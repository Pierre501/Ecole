package controller;

import java.sql.SQLException;
import model.Directeur;
import model.ModelView;
import model.MyAnnotations;
import service.ServiceDirecteur;


public class CtrlDirecteur 
{
    private Directeur directeur = new Directeur();
    ServiceDirecteur service = new ServiceDirecteur();

    public void setDirecteur(Directeur directeur) 
    {
        this.directeur = directeur;
    }

    public Directeur getDirecteur() 
    {
        return directeur;
    }
    
    @MyAnnotations.Urlmapping(key="loginDirecteur", value="CtrlDirecteur_verifierLoginDirecteur.do")
    public ModelView verifierLoginDirecteur() throws SQLException
    {
        ModelView data = service.traitementLoginDirecteur(directeur);
        return data;
    }
}
