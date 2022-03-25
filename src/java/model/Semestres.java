package model;

import crud.Crud;


public class Semestres extends Crud
{
    private int id_semestres;
    private int id_annee_scolaire;
    private String semestres;

    public void setId_semestres(int id_semestres) 
    {
        this.id_semestres = id_semestres;
    }

    public void setId_annee_scolaire(int id_annee_scolaire) 
    {
        this.id_annee_scolaire = id_annee_scolaire;
    }

    public void setSemestres(String semestres) 
    {
        this.semestres = semestres;
    }

    public int getId_semestres() 
    {
        return id_semestres;
    }

    public int getId_annee_scolaire() 
    {
        return id_annee_scolaire;
    }

    public String getSemestres() 
    {
        return semestres;
    }

    public Semestres() 
    {
        
    }   
}
