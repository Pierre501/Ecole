package controller;

import java.sql.SQLException;
import model.ModelView;
import model.MyAnnotations;
import model.ViewEtudiants;
import service.ServiceViewEtudiants;


public class CtrlViewEtudiants 
{
    private ViewEtudiants viewEtudiants = new ViewEtudiants();
    ServiceViewEtudiants service = new ServiceViewEtudiants();

    public void setViewEtudiants(ViewEtudiants viewEtudiants) 
    {
        this.viewEtudiants = viewEtudiants;
    }

    public ViewEtudiants getViewEtudiants() 
    {
        return viewEtudiants;
    }
    
    @MyAnnotations.Urlmapping(key="recherche", value="CtrlViewEtudiants_rechercheMultiTache.do")
    public ModelView rechercheMultiTache() throws SQLException
    {
        ModelView data = service.traitementRechercheMultiTache(viewEtudiants);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="liste", value="CtrlViewEtudiants_listeEtudiants.do")
    public ModelView listeEtudiants() throws SQLException
    {
        ModelView data = service.traitementListeEtudiants(viewEtudiants);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="modifier", value="CtrlViewEtudiants_listeModificationEtudiants.do")
    public ModelView listeModificationEtudiants() throws SQLException
    {
        ModelView data = service.traitementListeModifierEtudiants(viewEtudiants);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="modif", value="CtrlViewEtudiants_modificationEtudiants.do")
    public ModelView modificationEtudiants() throws SQLException
    {
        ModelView data = service.traitementModificationEtudiants(viewEtudiants);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="ajouter", value="CtrlViewEtudiants_ajouterEtudiants.do")
    public ModelView ajouterEtudiants() throws SQLException
    {
        ModelView data = service.traitementAjouterEtudiants(viewEtudiants);
        return data;
    }
}
