package model;

import crud.Crud;


public class Classy extends Crud 
{
    private int id_classy;
    private String classy;

    public void setId_classy(int id_classy) 
    {
        this.id_classy = id_classy;
    }

    public void setClassy(String classy) 
    {
        this.classy = classy;
    }

    public int getId_classy() 
    {
        return id_classy;
    }

    public String getClassy() 
    {
        return classy;
    }

    public Classy() 
    {
        
    }

    public Classy(String classy) 
    {
        this.classy = classy;
    }    
}
