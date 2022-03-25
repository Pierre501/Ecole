package model;

import crud.Crud;


public class Classy_etudiants extends Crud
{
    private int id_classy_etudiants;
    private int id_etudiants;
    private int id_classy;

    public void setId_classy_etudiants(int id_classy_etudiants) 
    {
        this.id_classy_etudiants = id_classy_etudiants;
    }

    public void setId_etudiants(int id_etudiants) 
    {
        this.id_etudiants = id_etudiants;
    }

    public void setId_classy(int id_classy) 
    {
        this.id_classy = id_classy;
    }

    public int getId_classy_etudiants() 
    {
        return id_classy_etudiants;
    }

    public int getId_etudiants() 
    {
        return id_etudiants;
    }

    public int getId_classy() 
    {
        return id_classy;
    }

    public Classy_etudiants() 
    {
        
    }

    public Classy_etudiants(int id_etudiants, int id_classy) 
    {
        this.id_etudiants = id_etudiants;
        this.id_classy = id_classy;
    }   
}
