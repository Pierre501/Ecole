package controller;

import java.sql.SQLException;
import model.ModelView;
import model.MyAnnotations;
import model.Validation_notes;
import service.ServiceValidationNotes;


public class CtrlValidationNotes 
{
    private Validation_notes validation_notes = new Validation_notes();
    ServiceValidationNotes service = new ServiceValidationNotes();

    public void setValidation_notes(Validation_notes validation_notes) 
    {
        this.validation_notes = validation_notes;
    }

    public Validation_notes getValidation_notes() 
    {
        return validation_notes;
    }
    
    @MyAnnotations.Urlmapping(key="validation", value="CtrlValidationNotes_validationDesNotes.do")
    public ModelView validationDesNotes() throws SQLException
    {
        ModelView data = service.traitementValidationNotes(validation_notes);
        return data;
    }
}
