package model;


public class ResultatFinal 
{
    private String etu;
    private double moyenneFianle;
    private String mentionsFinale;
    private String decisinFinal;
    private Notes[] notes;

    public void setEtu(String etu)
    {
        this.etu = etu;
    }
    
    public void setMoyenneFianle(double moyenneFianle) 
    {
        this.moyenneFianle = moyenneFianle;
    }

    public void setMentionsFinale(String mentionsFinale) 
    {
        this.mentionsFinale = mentionsFinale;
    }

    public void setDecisinFinal(String decisinFinal) 
    {
        this.decisinFinal = decisinFinal;
    }

    public void setNotes(Notes[] notes) 
    {
        this.notes = notes;
    }
    
    public String getEtu()
    {
        return etu;
    }

    public double getMoyenneFianle() 
    {
        return moyenneFianle;
    }

    public String getMentionsFinale() 
    {
        return mentionsFinale;
    }

    public String getDecisinFinal() 
    {
        return decisinFinal;
    }

    public Notes[] getNotes() 
    {
        return notes;
    }

    public ResultatFinal() 
    {
        
    }

    public ResultatFinal(String etu, double moyenneFianle, String mentionsFinale, String decisinFinal, Notes[] notes) 
    {
        this.etu = etu;
        this.moyenneFianle = moyenneFianle;
        this.mentionsFinale = mentionsFinale;
        this.decisinFinal = decisinFinal;
        this.notes = notes;
    }   
}
