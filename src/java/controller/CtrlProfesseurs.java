package controller;

import java.sql.SQLException;
import model.ModelView;
import model.MyAnnotations;
import model.Professeurs;
import service.ServiceProfesseurs;


public class CtrlProfesseurs 
{
    private Professeurs professeurs = new Professeurs();
    ServiceProfesseurs service = new ServiceProfesseurs();

    public void setProfesseurs(Professeurs professeurs) 
    {
        this.professeurs = professeurs;
    }

    public Professeurs getProfesseurs() 
    {
        return professeurs;
    }
    
    @MyAnnotations.Urlmapping(key="login", value="CtrlProfesseurs_verifierLogin.do")
    public ModelView verifierLogin() throws SQLException
    {
        ModelView data = service.traitementVerifierLogin(professeurs);
        return data;
    }
}
