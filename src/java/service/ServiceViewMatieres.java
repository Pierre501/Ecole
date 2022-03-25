package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import model.Classy;
import model.Connexion;
import model.Matieres;
import model.ModelView;
import model.Notes;
import model.ResultatFinal;
import model.Semestres;
import model.ViewEtudiants;
import model.ViewMatieres;


public class ServiceViewMatieres 
{
    Connexion connex = new Connexion();
    
    public ViewMatieres[] getAllViewMatieres(ViewMatieres viewMatieres, Connection con) throws SQLException
    {
        Object[] tabObject = viewMatieres.findObject(con);
        ViewMatieres[] tabViewMatieres = new ViewMatieres[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabViewMatieres[i] = (ViewMatieres) tabObject[i];
        }
        return tabViewMatieres;
    }
    
    public int[] getCoefficients(ViewMatieres viewMatieres, Connection con) throws SQLException
    {
        ViewMatieres[] tabViewMatieres = getAllViewMatieres(viewMatieres, con);
        int[] tabCoefficient = new int[tabViewMatieres.length];
        for(int i = 0; i < tabViewMatieres.length; i++)
        {
            tabCoefficient[i] = tabViewMatieres[i].getCoefficients();
        }
        return tabCoefficient;
    }
    
    public double[] getNotes(ViewMatieres viewMatieres, Connection con) throws SQLException
    {
        ViewMatieres[] tabViewMatieres = getAllViewMatieres(viewMatieres, con);
        double[] tabNotes = new double[tabViewMatieres.length];
        for(int i = 0; i < tabViewMatieres.length; i++)
        {
            tabNotes[i] = tabViewMatieres[i].getNotes();
        }
        return tabNotes;
    }
    
    public ModelView traitementNotes(ViewMatieres viewMatieres) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        ViewEtudiants etu = new ViewEtudiants();
        etu.setEtu(viewMatieres.getEtu());
        ViewEtudiants etudiants = null;
        Object[] tabObject = etu.findObject(con);
        for(int i = 0; i < tabObject.length; i++)
        {
            etudiants = (ViewEtudiants) tabObject[i];
            break;
        }
        viewMatieres.setClassy(etudiants.getClassy());
        ViewMatieres[] tabViewMatiere = getAllViewMatieres(viewMatieres, con);
        int[] tabCoefficient = getCoefficients(viewMatieres, con);
        double[] tabNotes = getNotes(viewMatieres, con);
        double moyenne = calculMoyenne(tabNotes, tabCoefficient);
        Notes notes = new Notes();
        notes.setViewEtudiants(etudiants);
        notes.setViewMatieres(tabViewMatiere);
        notes.setMoyenne(moyenne);
        notes.setMentions(mentions(moyenne));
        ServiceSemestres serviceSemestres = new ServiceSemestres();
        Semestres semestres = new Semestres();
        ModelView model2 = (ModelView) serviceSemestres.traitementListeSemestres(semestres);
        Semestres[] tabSemestres = (Semestres[]) model2.getData().get("tabSemestres");
        ViewEtudiants etudiant = getSimpleViewEtudiants(viewMatieres, con);
        HashMap data = new HashMap();
        data.put("tabSemestres", tabSemestres);
        data.put("notes", notes);
        data.put("etudiant", etudiant);
        retour = new ModelView(data, "/bulletin.jsp");
        con.close();
        return retour;
    }
    
    public ModelView traitementResultatFinale(ViewMatieres viewMatieres) throws SQLException
    {
        ResultatFinal[] tabResultatFinal = getAllResultatFinal(viewMatieres);
        ResultatFinal[] resultatAdmis = getListeAdmis(tabResultatFinal);
        ResultatFinal[] resultatNonAdmis = getListeNonAdmis(tabResultatFinal);
        ServiceClassy serviceClassy = new ServiceClassy();
        Classy classy = new Classy();
        ModelView temp = serviceClassy.traitementListeClasses4(classy);
        Classy[] tabClassy = (Classy[]) temp.getData().get("classes");
        HashMap data = new HashMap();
        data.put("classes", tabClassy);
        data.put("resultatAdmis", resultatAdmis);
        data.put("resultatNonAdmis", resultatNonAdmis);
        ModelView model = new ModelView(data, "/resultat.jsp");
        return model;
    }
    
    public ModelView traitementEnregistrementDesNotes(ViewMatieres viewMatieres) throws SQLException
    {
        Connection con = connex.connexion();
        Matieres[] tabMatieres = getAllMatieres(con);
        Semestres[] tabSemestres = getAllSemestres(con);
        HashMap data = new HashMap();
        data.put("tabMatieres", tabMatieres);
        data.put("tabSemestres", tabSemestres);
        ModelView retour = new ModelView(data, "/enregistrement.jsp");
        con.close();
        return retour;
    }
    
    public ModelView traitementEnregistrementDesNotesEtudiants(ViewMatieres viewMatieres) throws SQLException
    {
        Connection con = connex.connexion();
        insertNotesEtudiants(viewMatieres.getEtu(), viewMatieres.getNom_matieres(), viewMatieres.getNotes(), viewMatieres.getSemestres(), con);
        con.close();
        ModelView retour = traitementEnregistrementDesNotes(viewMatieres);
        return retour;
    }
    
    public void insertNotesEtudiants(String etu, String nomMatieres, double notes, String semestres, Connection con) throws SQLException
    {
        PreparedStatement pstmt = con.prepareStatement("insert into validation_notes (etu, nom_matieres, notes, semestres)values(?, ?, ?, ?)");
        pstmt.setString(1, etu);
        pstmt.setString(2, nomMatieres);
        pstmt.setDouble(3, notes);
        pstmt.setString(4, semestres);
        pstmt.executeUpdate();
        con.commit();
    }
    
    public Matieres[] getAllMatieres(Connection con) throws SQLException
    {
        ArrayList liste = new ArrayList();
        String sql = "select * from matieres";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
        {
            Matieres matieres = new Matieres(rs.getInt("id_matieres"), rs.getString("nom_matieres"));
            liste.add(matieres);
        }
        Matieres[] tabMatieres = new Matieres[liste.size()];
        for(int i = 0; i < liste.size(); i++)
        {
            tabMatieres[i] = (Matieres) liste.get(i);
        }
        return tabMatieres;
    }        
    
    public ResultatFinal[] getListeTrierParMention(ResultatFinal[] tabResultatFinal)
    {
        for(int i = 0; i < tabResultatFinal.length; i++)
        {
            for(int j = i + 1; j < tabResultatFinal.length; j++)
            {
                if(tabResultatFinal[i].getMoyenneFianle() > tabResultatFinal[j].getMoyenneFianle())
                {
                    ResultatFinal temp = tabResultatFinal[i];
                    tabResultatFinal[i] = tabResultatFinal[j];
                    tabResultatFinal[j] = temp;
                }
            }
        }
        return tabResultatFinal;
    }
    
    public ResultatFinal[] getListeAdmis(ResultatFinal[] tabResultatFinal)
    {
        ArrayList listeAdmis = new ArrayList();
        for(int i = 0; i < tabResultatFinal.length; i++)
        {
            if(tabResultatFinal[i].getMoyenneFianle() >= 10 && tabResultatFinal[i].getMoyenneFianle() <= 20)
            {
                listeAdmis.add(tabResultatFinal[i]);
            }
        } 
        ResultatFinal[] resultat = new ResultatFinal[listeAdmis.size()];
        for(int j = 0; j < listeAdmis.size(); j++)
        {
            resultat[j] = (ResultatFinal) listeAdmis.get(j);
        }
        ResultatFinal[] resultatFinal = getListeTrierParMention(resultat);
        return resultatFinal;
    }
    
    public ResultatFinal[] getListeNonAdmis(ResultatFinal[] tabResultatFinal)
    {
        ArrayList listeNonAdmis = new ArrayList();
        for(int i = 0; i < tabResultatFinal.length; i++)
        {
            if(tabResultatFinal[i].getMoyenneFianle() < 10)
            {
                listeNonAdmis.add(tabResultatFinal[i]);
            }
        } 
        ResultatFinal[] resultat = new ResultatFinal[listeNonAdmis.size()];
        for(int j = 0; j < listeNonAdmis.size(); j++)
        {
            resultat[j] = (ResultatFinal) listeNonAdmis.get(j);
        }
        ResultatFinal[] resultatFinal = getListeTrierParMention(resultat);
        return resultatFinal;
    }
    
    public ViewEtudiants getSimpleViewEtudiants(ViewMatieres viewMatieres, Connection con)
    {
        ViewEtudiants etudiants = null;
        ViewEtudiants etu = new ViewEtudiants();
        etu.setEtu(viewMatieres.getEtu());
        Object[] tabObject = etu.findObject(con);
        for(int i = 0; i < tabObject.length; i++)
        {
            etudiants = (ViewEtudiants) tabObject[i];
            break;
        }
        return etudiants;
    }
    
    public ModelView traitementDetailResultatFinale(ViewMatieres viewMatieres) throws SQLException
    {
        Connection con = connex.connexion();
        Semestres semestres = new Semestres();
        Object[] tabObject = semestres.getAllObject(con);
        Semestres[] tabSemestres = new Semestres[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabSemestres[i] = (Semestres) tabObject[i];
        }
        ViewEtudiants etudiants = getSimpleViewEtudiants(viewMatieres, con);
        ResultatFinal resultatFinal = getSimpleResultatFinal(etudiants, con);
        HashMap data = new HashMap();
        data.put("resultatFinal", resultatFinal);
        data.put("tabSemestres", tabSemestres);
        con.close();
        ModelView model = new ModelView(data, "/detailsResultat.jsp");
        return model;
    }
    
    public ViewEtudiants[] getAllViewEtudiants(ViewMatieres viewMatieres, Connection con)
    {
        ViewEtudiants etudiants = new ViewEtudiants();
        etudiants.setClassy(viewMatieres.getClassy());
        Object[] tabObject = etudiants.findObject(con);
        ViewEtudiants[] tabViewEtudiants = new ViewEtudiants[tabObject.length];
        for(int i = 0; i< tabObject.length; i++)
        {
            tabViewEtudiants[i] = (ViewEtudiants) tabObject[i];
        }
        return tabViewEtudiants;
    }
    
    public Semestres[] getAllSemestres(Connection con)
    {
        Semestres semestres = new Semestres();
        Object[] tabObject = semestres.getAllObject(con);
        Semestres[] tabSemestres = new Semestres[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabSemestres[i] = (Semestres) tabObject[i];
        }
        return tabSemestres;
    }
    
    public ViewMatieres[] getAllViewMatieres(ViewEtudiants etudiants, String semestres, Connection con)
    {
        ViewMatieres viewMatieres = new ViewMatieres();
        viewMatieres.setEtu(etudiants.getEtu());
        viewMatieres.setClassy(etudiants.getClassy());
        viewMatieres.setSemestres(semestres);
        Object[] tabObject = viewMatieres.findObject(con);
        ViewMatieres[] tabViewMatieres = new ViewMatieres[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabViewMatieres[i] = (ViewMatieres) tabObject[i];
        }
        return tabViewMatieres;
    }
    
    public Notes[] getAllNotes(ViewEtudiants etudiants, Connection con)
    {
        Notes[] tabNotes = null;
        Semestres[] tabSemestres = getAllSemestres(con);
        ArrayList listeNote = new ArrayList();
        for(int i = 0; i < tabSemestres.length; i++)
        {
            ViewMatieres[] viewMatieres = getAllViewMatieres(etudiants, tabSemestres[i].getSemestres(), con);
            int[] tabCoefficient = getAllCoefficient(viewMatieres);
            double[] tabNotes2 = getAllValeurNotes(viewMatieres);
            double moyenne = calculMoyenne(tabNotes2, tabCoefficient);
            Notes notes = new Notes();
            notes.setViewEtudiants(etudiants);
            notes.setViewMatieres(viewMatieres);
            notes.setMoyenne(moyenne);
            notes.setMentions(mentions(moyenne));
            listeNote.add(notes);
        }
        tabNotes = new Notes[listeNote.size()];
        for(int j = 0; j < listeNote.size(); j++)
        {
            tabNotes[j] = (Notes) listeNote.get(j);
        }
        return tabNotes;
    }
    
    public String getDecisionFinal(double moyenne)
    {
        String decision = null;
        if(moyenne < 10)
        {
            decision = "Non admis";
        }
        else if(moyenne >= 10 && moyenne <= 20)
        {
            decision = "Admis";
        }
        return decision;
    }
    
    public ResultatFinal getSimpleResultatFinal(ViewEtudiants etudiants, Connection con)
    {
        Notes[] tabNotes = getAllNotes(etudiants, con);
        int diviseMoyenne = 0;
        double sommeMoyenne = 0.0;
        for(int i = 0; i < tabNotes.length; i++)
        {
            sommeMoyenne = sommeMoyenne + tabNotes[i].getMoyenne();
            diviseMoyenne++;
        }
        String etu = etudiants.getEtu();
        double moyenneFinal = sommeMoyenne / diviseMoyenne;
        String mentionFinal = mentions(moyenneFinal);
        String decisionFinal = getDecisionFinal(moyenneFinal);
        ResultatFinal resultat = new ResultatFinal(etu, moyenneFinal, mentionFinal, decisionFinal, tabNotes);
        return resultat;
    }
    
    public ResultatFinal[] getAllResultatFinal(ViewMatieres viewMatieres) throws SQLException
    {
        Connection con = connex.connexion();
        ArrayList listeResultat = new ArrayList();
        ViewEtudiants[] tabViewEtudiants = getAllViewEtudiants(viewMatieres, con);
        for(int i = 0; i< tabViewEtudiants.length; i++)
        {
            ResultatFinal resultat = getSimpleResultatFinal(tabViewEtudiants[i], con);
            listeResultat.add(resultat);
        }
        ResultatFinal[] tabResultatFinal = new ResultatFinal[listeResultat.size()];
        for(int j = 0; j < listeResultat.size(); j++)
        {
            tabResultatFinal[j] = (ResultatFinal) listeResultat.get(j);
        }
        con.close();
        return tabResultatFinal;
    }
    
    public int[] getAllCoefficient(ViewMatieres[] viewMatieres)
    {
        int[] tabCoefficient = new int[viewMatieres.length];
        for(int i = 0; i < viewMatieres.length; i++)
        {
            tabCoefficient[i] = viewMatieres[i].getCoefficients();
        }
        return tabCoefficient;
    }
    
    public double[] getAllValeurNotes(ViewMatieres[] viewMatieres)
    {
        double[] tabNotes = new double[viewMatieres.length];
        for(int i = 0; i < tabNotes.length; i++)
        {
            tabNotes[i] = viewMatieres[i].getNotes();
        }
        return tabNotes;
    }
    
    public int calculTotalCoeff(int[] tabCoeff)
    {
	int totalCoeff = 0;
	for(int i = 0; i < tabCoeff.length; i++)
	{
            totalCoeff = totalCoeff + tabCoeff[i];
	}
	return totalCoeff;
    }
    
    public double calculMoyenne(double[] tabNote,int[] tabCoeff)
    {
	double moyenne = 0;
	double totalNote = 0;
	for(int i = 0; i < tabNote.length; i++)
	{
            totalNote = totalNote + (tabNote[i] * tabCoeff[i]);
	}
	moyenne = totalNote / calculTotalCoeff(tabCoeff);
        return moyenne;
    }
    
    public String mentions(double moyenne)
    {
	String mention ="";
	if(moyenne >= 16) 
        {
            mention = "Tres Bien";
	}
        else if(moyenne >= 14 && moyenne < 16)
        {
            mention = "Bien";
	}
        else if(moyenne >= 12 && moyenne < 14)
        {
            mention = "Assez Bien";
	}
        else if(moyenne >= 10 && moyenne < 12)
        {
            mention = "Passable";
	}
        else if(moyenne < 10)
        {
            mention = "Récalé";
	}
	return mention;
    }
}
