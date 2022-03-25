package model;

import crud.Crud;


public class Matiere extends Crud
{
    private int id_matieres;
    private String nom_matieres;
    private int id_coefficient;
    private int coefficients;
    private int id_classy;
    private String classy;

    public void setId_matieres(int id_matieres) 
    {
        this.id_matieres = id_matieres;
    }

    public void setNom_matieres(String nom_matieres) 
    {
        this.nom_matieres = nom_matieres;
    }

    public void setId_coefficient(int id_coefficient) 
    {
        this.id_coefficient = id_coefficient;
    }

    public void setCoefficients(int coefficients) 
    {
        this.coefficients = coefficients;
    }

    public void setId_classy(int id_classy) 
    {
        this.id_classy = id_classy;
    }

    public void setClassy(String classy) 
    {
        this.classy = classy;
    }

    public int getId_matieres() 
    {
        return id_matieres;
    }

    public String getNom_matieres() 
    {
        return nom_matieres;
    }

    public int getId_coefficient() 
    {
        return id_coefficient;
    }

    public int getCoefficients() 
    {
        return coefficients;
    }

    public int getId_classy() 
    {
        return id_classy;
    }

    public String getClassy() 
    {
        return classy;
    }

    public Matiere() 
    {
        
    }    
}
