package controller;

import java.sql.SQLException;
import model.ModelView;
import model.MyAnnotations;
import model.Semestres;
import service.ServiceSemestres;


public class CtrlSemestres 
{
    private Semestres semestres = new Semestres();
    ServiceSemestres service = new ServiceSemestres();

    public void setSemestres(Semestres semestres) 
    {
        this.semestres = semestres;
    }

    public Semestres getSemestres() 
    {
        return semestres;
    }
    
    @MyAnnotations.Urlmapping(key="listeSemestres", value="CtrlSemestres_listeSemestres.do")
    public ModelView listeSemestres() throws SQLException
    {
        ModelView data = service.traitementListeSemestres(semestres);
        return data;
    }
}
