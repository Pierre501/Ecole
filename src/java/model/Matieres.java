package model;

import crud.Crud;


public class Matieres extends Crud
{
    private int id_matieres;
    private String nom_matieres;

    public void setId_matieres(int id_matieres) 
    {
        this.id_matieres = id_matieres;
    }

    public void setNom_matieres(String nom_matieres) 
    {
        this.nom_matieres = nom_matieres;
    }

    public int getId_matieres() 
    {
        return id_matieres;
    }

    public String getNom_matieres() 
    {
        return nom_matieres;
    }

    public Matieres(int id_matieres, String nom_matieres) 
    {
        this.id_matieres = id_matieres;
        this.nom_matieres = nom_matieres;
    } 

    public Matieres() 
    {
        
    }   
}
