package model;

import crud.Crud;


public class ViewMatieres extends Crud
{
    private int id_notes;
    private int id_semestres;
    private String semestres;
    private int id_etudiants;
    private double notes;
    private int id_matieres;
    private String nom_matieres;
    private int id_coefficient;
    private int coefficients;
    private int id_classy;
    private String classy;
    private String etu;

    public void setId_semestres(int id_semestres) 
    {
        this.id_semestres = id_semestres;
    }

    public void setSemestres(String semestres) 
    {
        this.semestres = semestres;
    }

    public void setId_notes(int id_notes) 
    {
        this.id_notes = id_notes;
    }

    public void setId_etudiants(int id_etudiants) 
    {
        this.id_etudiants = id_etudiants;
    }

    public void setNotes(double notes) 
    {
        this.notes = notes;
    }

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
    
    public void setEtu(String etu)
    {
        this.etu = etu;
    }

    public int getId_semestres() 
    {
        return id_semestres;
    }

    public String getSemestres() 
    {
        return semestres;
    }

    public int getId_notes() 
    {
        return id_notes;
    }

    public int getId_etudiants() 
    {
        return id_etudiants;
    }

    public double getNotes() 
    {
        return notes;
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
    
    public String getEtu()
    {
        return etu;
    }

    public ViewMatieres() 
    {
        
    } 
    
    public String mention(double moyenne)
    {
	String mention ="";
	if(moyenne >= 16) 
        {
            mention = "TB";
	}
        else if(moyenne >= 14 && moyenne < 16)
        {
            mention = "B";
	}
        else if(moyenne >= 12 && moyenne < 14)
        {
            mention = "AB";
	}
        else if(moyenne >= 10 && moyenne < 12)
        {
            mention = "P";
	}
        else if(moyenne < 10)
        {
            mention = "A jour";
	}
	return mention;
    }
}
