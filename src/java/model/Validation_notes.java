package model;

import crud.Crud;


public class Validation_notes extends Crud
{
    private int id_validation_notes;
    private String etu;
    private String nom_matieres;
    private double notes;
    private String semestres;

    public void setId_validation_notes(int id_validation_notes) 
    {
        this.id_validation_notes = id_validation_notes;
    }

    public void setEtu(String etu) 
    {
        this.etu = etu;
    }

    public void setNom_matieres(String nom_matieres) 
    {
        this.nom_matieres = nom_matieres;
    }

    public void setNotes(double notes) {
        this.notes = notes;
    }

    public void setSemestres(String semestres) 
    {
        this.semestres = semestres;
    }

    public int getId_validation_notes() 
    {
        return id_validation_notes;
    }

    public String getEtu() 
    {
        return etu;
    }

    public String getNom_matieres() 
    {
        return nom_matieres;
    }

    public double getNotes() 
    {
        return notes;
    }

    public String getSemestres() 
    {
        return semestres;
    }    
}
