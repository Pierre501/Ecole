package controller;

import java.sql.SQLException;
import model.ModelView;
import model.MyAnnotations;
import model.ViewMatieres;
import service.ServiceViewMatieres;


public class CtrlViewMatieres 
{
    private ViewMatieres viewMatieres = new ViewMatieres();
    ServiceViewMatieres service = new ServiceViewMatieres();

    public void setViewMatieres(ViewMatieres viewMatieres) 
    {
        this.viewMatieres = viewMatieres;
    }

    public ViewMatieres getViewMatieres() 
    {
        return viewMatieres;
    }
    
    @MyAnnotations.Urlmapping(key="notes", value="CtrlViewMatieres_notesEtudiants.do")
    public ModelView notesEtudiants() throws SQLException
    {
        ModelView data = service.traitementNotes(viewMatieres);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="resultat", value="CtrlViewMatieres_resultatFinal.do")
    public ModelView resultatFinal() throws SQLException
    {
        ModelView data = service.traitementResultatFinale(viewMatieres);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="detailsResultat", value="CtrlViewMatieres_detailsResultatFinal.do")
    public ModelView detailsResultatFinal() throws SQLException
    {
        ModelView data = service.traitementDetailResultatFinale(viewMatieres);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="enregistrement", value="CtrlViewMatieres_enregistrementDesNotes.do")
    public ModelView enregistrementDesNotes() throws SQLException
    {
        ModelView data = service.traitementEnregistrementDesNotes(viewMatieres);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="enregistrer", value="CtrlViewMatieres_enregistrementDesNotesEtudiants.do")
    public ModelView enregistrementDesNotesEtudiants() throws SQLException
    {
        ModelView data = service.traitementEnregistrementDesNotesEtudiants(viewMatieres);
        return data;
    }
}
