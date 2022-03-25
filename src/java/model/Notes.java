package model;


public class Notes 
{
    private ViewEtudiants etudiants;
    private ViewMatieres[] viewMatieres;
    private double moyenne;
    private String mentions;

    public void setViewEtudiants(ViewEtudiants etudiants) 
    {
        this.etudiants = etudiants;
    }

    public void setViewMatieres(ViewMatieres[] viewMatieres) 
    {
        this.viewMatieres = viewMatieres;
    }

    public void setMoyenne(double moyenne) 
    {
        this.moyenne = moyenne;
    }

    public void setMentions(String mentions) 
    {
        this.mentions = mentions;
    }

    public ViewEtudiants getViewEtudiants() 
    {
        return etudiants;
    }

    public ViewMatieres[] getViewMatieres() 
    {
        return viewMatieres;
    }

    public double getMoyenne() 
    {
        return moyenne;
    }

    public String getMentions() 
    {
        return mentions;
    }

    public Notes() 
    {
        
    }
}
