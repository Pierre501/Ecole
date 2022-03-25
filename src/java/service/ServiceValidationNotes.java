package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import model.Connexion;
import model.Etudiants;
import model.Matieres;
import model.ModelView;
import model.Semestres;
import model.Validation_notes;


public class ServiceValidationNotes 
{
    Connexion connex = new Connexion();
    
    public Validation_notes getSimpleValidationNotes(Validation_notes validation_notes, Connection con)
    {
        Validation_notes retour = (Validation_notes) validation_notes.getSimpleObject(con, validation_notes.getId_validation_notes());
        return retour;
    }
    
    public Etudiants getSimpleEtudiants(Validation_notes validationNotes, Connection con)
    {
        Etudiants etudiants = null;
        Etudiants etu = new Etudiants();
        etu.setEtu(validationNotes.getEtu());
        Object[] tabObject = etu.findObject(con);
        for(int i = 0; i < tabObject.length; i++)
        {
            etudiants = (Etudiants) tabObject[i];
            break;
        }
        return etudiants;
    }
    
    public Matieres getSimpleMatieres(Validation_notes validationNotes, Connection con)
    {
        Matieres matieres = null;
        Matieres matiere = new Matieres();
        matiere.setNom_matieres(validationNotes.getNom_matieres());
        Object[] tabObject = matiere.findObject(con);
        for(int i = 0; i < tabObject.length; i++)
        {
            matieres = (Matieres) tabObject[i];
            break;
        }
        return matieres;
    }
    
    public Semestres getSimpleSemestres(Validation_notes validationNotes, Connection con)
    {
        Semestres semestres = null; 
        Semestres semestre = new Semestres();
        semestre.setSemestres(validationNotes.getSemestres());
        Object[] tabObject = semestre.findObject(con);
        for(int i = 0; i < tabObject.length; i++)
        {
            semestres = (Semestres) tabObject[i];
            break;
        }
        return semestres;
    }
    
    public void modifierNotes(Validation_notes validationNotes, Connection con) throws SQLException
    {
        Validation_notes validation = getSimpleValidationNotes(validationNotes, con);
        Etudiants etudiants = getSimpleEtudiants(validation, con);
        Matieres matieres = getSimpleMatieres(validation, con);
        Semestres semestres = getSimpleSemestres(validation, con);
        PreparedStatement pstmt = con.prepareStatement("update notes set notes = ? where id_etudiants = ? and id_matieres = ? and id_semestres = ?");
        pstmt.setDouble(1, validation.getNotes());
        pstmt.setInt(2, etudiants.getId_etudiants());
        pstmt.setInt(3, matieres.getId_matieres());
        pstmt.setInt(4, semestres.getId_semestres());
        pstmt.executeUpdate();
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
    
    public ModelView traitementValidationNotes(Validation_notes validationNotes) throws SQLException
    {
        Connection con = connex.connexion();
        modifierNotes(validationNotes, con);
        Validation_notes validation = getSimpleValidationNotes(validationNotes, con);
        validation.supprimerBdd(con);
        Validation_notes[] tabValidation = getAllValidationNotes(con);
        HashMap data = new HashMap();
        data.put("tabValidation", tabValidation);
        ModelView model = new ModelView(data, "/validation.jsp");
        con.close();
        return model;
    }
}




