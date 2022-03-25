package model;

import crud.Crud;
import java.sql.Date;


public class ViewEtudiants extends Crud
{
    private int id_etudiants;
    private String etu;
    private String nom_etudiants;
    private String prenom_etudiants;
    private Date date_de_naissance;
    private String genre;
    private String adresse;
    private String lieu_de_naissance;
    private String photo_etudiants;
    private int id_classy;
    private String classy;

    public void setId_etudiants(int id_etudiants) 
    {
        this.id_etudiants = id_etudiants;
    }

    public void setEtu(String etu) 
    {
        this.etu = etu;
    }

    public void setNom_etudiants(String nom_etudiants) 
    {
        this.nom_etudiants = nom_etudiants;
    }

    public void setPrenom_etudiants(String prenom_etudiants) 
    {
        this.prenom_etudiants = prenom_etudiants;
    }

    public void setDate_de_naissance(String date_de_naissance) 
    {
        this.date_de_naissance = Date.valueOf(date_de_naissance);
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }

    public void setAdresse(String adresse) 
    {
        this.adresse = adresse;
    }

    public void setLieu_de_naissance(String lieu_de_naissance) 
    {
        this.lieu_de_naissance = lieu_de_naissance;
    }

    public void setPhoto_etudiants(String photo_etudiants) 
    {
        this.photo_etudiants = photo_etudiants;
    }

    public void setId_classy(int id_classy) 
    {
        this.id_classy = id_classy;
    }

    public void setClassy(String classy) 
    {
        this.classy = classy;
    }

    public int getId_etudiants() 
    {
        return id_etudiants;
    }

    public String getEtu() 
    {
        return etu;
    }

    public String getNom_etudiants() 
    {
        return nom_etudiants;
    }

    public String getPrenom_etudiants() 
    {
        return prenom_etudiants;
    }

    public Date getDate_de_naissance() 
    {
        return date_de_naissance;
    }

    public String getGenre() 
    {
        return genre;
    }

    public String getAdresse() 
    {
        return adresse;
    }

    public String getLieu_de_naissance() 
    {
        return lieu_de_naissance;
    }

    public String getPhoto_etudiants() 
    {
        return photo_etudiants;
    }

    public int getId_classy() 
    {
        return id_classy;
    }

    public String getClassy() 
    {
        return classy;
    }

    public ViewEtudiants() 
    {
        
    } 
    
    public String formatDate(Date date)
    {
        String[] formatDate = date.toString().split("-");
        String retour = formatDate[2]+"/"+formatDate[1]+"/"+formatDate[0];
        return retour;
    }
}
