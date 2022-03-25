package controller;

import java.sql.SQLException;
import model.Classy;
import model.ModelView;
import model.MyAnnotations;
import service.ServiceClassy;


public class CtrlClasses 
{
    private Classy classes = new Classy();
    ServiceClassy service = new ServiceClassy();

    public void setClasses(Classy classes) 
    {
        this.classes = classes;
    }

    public Classy getClasses() 
    {
        return classes;
    }
    
    @MyAnnotations.Urlmapping(key="listeClasses", value="CtrlClasses_listeClasses.do")
    public ModelView listeClasses() throws SQLException
    {
        ModelView data = service.traitementListeClasses(classes);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="listeClasses2", value="CtrlClasses_listeClasses2.do")
    public ModelView listeClasses2() throws SQLException
    {
        ModelView data = service.traitementListeClasses2(classes);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="listeClasses3", value="CtrlClasses_listeClasses3.do")
    public ModelView listeClasses3() throws SQLException
    {
        ModelView data = service.traitementListeClasses3(classes);
        return data;
    }
    
    @MyAnnotations.Urlmapping(key="listeClasses4", value="CtrlClasses_listeClasses4.do")
    public ModelView listeClasses4() throws SQLException
    {
        ModelView data = service.traitementListeClasses4(classes);
        return data;
    }
}
