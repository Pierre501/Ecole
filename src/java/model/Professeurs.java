package model;

import crud.Crud;


public class Professeurs extends Crud
{
    private int id_professeurs;
    private String username;
    private String mot_de_passe;

    public void setId_professeurs(int id_professeurs) 
    {
        this.id_professeurs = id_professeurs;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public void setMot_de_passe(String mot_de_passe) 
    {
        this.mot_de_passe = mot_de_passe;
    }

    public int getId_professeurs() 
    {
        return id_professeurs;
    }

    public String getUsername() 
    {
        return username;
    }

    public String getMot_de_passe() 
    {
        return mot_de_passe;
    }

    public Professeurs() 
    {
        
    }
}
