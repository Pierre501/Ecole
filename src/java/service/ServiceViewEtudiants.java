package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import model.Classy;
import model.Classy_etudiants;
import model.Connexion;
import model.Etudiants;
import model.Matiere;
import model.ModelView;
import model.Semestres;
import model.ViewEtudiants;


public class ServiceViewEtudiants 
{
    Connexion connex = new Connexion();
    
    public ModelView traitementRechercheMultiTache(ViewEtudiants viewEtudiants) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Object[] tabObject = viewEtudiants.findObject(con);
        ViewEtudiants[] tabViewEtudiants = new ViewEtudiants[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabViewEtudiants[i] = (ViewEtudiants)tabObject[i];
        }
        ServiceClassy serviceClasses = new ServiceClassy();
        Classy classes = new Classy();
        ModelView model = serviceClasses.traitementListeClasses(classes);
        Classy[] tabClasses = (Classy[]) model.getData().get("classes");
        HashMap data = new HashMap();
        data.put("tabViewEtudiants", tabViewEtudiants);
        data.put("classes", tabClasses);
        retour = new ModelView(data,"/recherche.jsp");
        return retour;
    }
    
    public ModelView traitementListeEtudiants(ViewEtudiants viewEtudiants) throws SQLException
    {
        ModelView model = traitementRechercheMultiTache(viewEtudiants);
        HashMap data = (HashMap) model.getData();
        ModelView retour = new ModelView(data, "/liste.jsp");
        return retour;
    }
    
    public ModelView traitementListeModifierEtudiants(ViewEtudiants viewEtudiants) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Object[] tabObject = viewEtudiants.findObject(con);
        ViewEtudiants etudiants = null;
        for(int i = 0; i < tabObject.length; i++)
        {
            etudiants = (ViewEtudiants) tabObject[i];
            break;
        }
        HashMap data = new HashMap();
        data.put("viewEtudiants", etudiants);
        retour = new ModelView(data, "/modif.jsp");
        con.close();
        return retour;
    }
    
    public ModelView traitementModificationEtudiants(ViewEtudiants viewEtudiants) throws SQLException
    {
        ModelView retour = null;
        Connection con = connex.connexion();
        Classy classy = new Classy();
        classy.setId_classy(viewEtudiants.getId_classy());
        classy.setClassy(viewEtudiants.getClassy());
        classy.modifierBdd(con);
        Etudiants etudiants = new Etudiants();
        etudiants.setId_etudiants(viewEtudiants.getId_etudiants());
        etudiants.setEtu(viewEtudiants.getEtu());
        etudiants.setNom_etudiants(viewEtudiants.getNom_etudiants());
        etudiants.setPrenom_etudiants(viewEtudiants.getPrenom_etudiants());
        etudiants.setDate_de_naissance(viewEtudiants.getDate_de_naissance().toString());
        etudiants.setLieu_de_naissance(viewEtudiants.getLieu_de_naissance());
        etudiants.setGenre(viewEtudiants.getGenre());
        etudiants.setAdresse(viewEtudiants.getAdresse());
        etudiants.setPhoto_etudiants(viewEtudiants.getPhoto_etudiants());
        etudiants.modifierBdd(con);
        con.close();
        retour = traitementListeEtudiants(viewEtudiants);
        return retour;
    }
    
    public ModelView traitementAjouterEtudiants(ViewEtudiants viewEtudiants) throws SQLException
    {
        Connection con = connex.connexion();
        Etudiants etudiants = new Etudiants(viewEtudiants.getEtu(), viewEtudiants.getNom_etudiants(), viewEtudiants.getPrenom_etudiants(), viewEtudiants.getDate_de_naissance().toString(), viewEtudiants.getGenre(), viewEtudiants.getAdresse(), viewEtudiants.getLieu_de_naissance(), viewEtudiants.getPhoto_etudiants());
        insertEtudiants(etudiants, con);
        Etudiants etudiants2 = getSimpleEtudiants(viewEtudiants.getEtu(), con);
        Classy classy2 = getSimpleClassy(viewEtudiants.getClassy(), con);
        Classy_etudiants classyEtudiants = new Classy_etudiants(etudiants2.getId_etudiants(), classy2.getId_classy());
        insertClassyEtudiants(classyEtudiants, con);
        Matiere[] tabMatiere = getAllMatiere(viewEtudiants.getClassy(), con);
        ServiceViewMatieres service = new ServiceViewMatieres();
        Semestres[] tabSemestres = service.getAllSemestres(con);
        insertToutNotesEtudiants(tabMatiere, tabSemestres, etudiants2, con);
        ModelView retour = traitementListeEtudiants(viewEtudiants);
        con.close();
        return retour;
    }
    
    public void insertEtudiants(Etudiants etudiants, Connection con) throws SQLException
    {
        PreparedStatement pstmt = con.prepareStatement("insert into etudiants (etu, nom_etudiants, prenom_etudiants, date_de_naissance, genre, adresse, lieu_de_naissance, photo_etudiants) values(?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setString(1, etudiants.getEtu());
        pstmt.setString(2, etudiants.getNom_etudiants());
        pstmt.setString(3, etudiants.getPrenom_etudiants());
        pstmt.setDate(4, etudiants.getDate_de_naissance());
        pstmt.setString(5, etudiants.getGenre());
        pstmt.setString(6, etudiants.getAdresse());
        pstmt.setString(7, etudiants.getLieu_de_naissance());
        pstmt.setString(8, etudiants.getPhoto_etudiants());
        pstmt.executeUpdate();
        con.commit();
    }
    
    public void insertClassyEtudiants(Classy_etudiants classyEtudiants, Connection con) throws SQLException
    {
        PreparedStatement pstmt = con.prepareStatement("insert into classy_etudiants (id_etudiants, id_classy) values(?, ?)");
        pstmt.setInt(1, classyEtudiants.getId_etudiants());
        pstmt.setInt(2, classyEtudiants.getId_classy());
        pstmt.executeUpdate();
        con.commit();
    }
    
    public void insertToutNotesEtudiants(Matiere[] tabMatiere, Semestres[] tabSemestres, Etudiants etudiants, Connection con) throws SQLException
    {
        double notes = 0.0;
        for(int i = 0; i < tabSemestres.length; i++)
        {
            for(int j = 0; j < tabMatiere.length; j++)
            {

                insertSimpleNoteEtudiants(etudiants.getId_etudiants(), tabMatiere[j].getId_matieres(), tabSemestres[i].getId_semestres(), notes, con);
            }
        }   
    }
    
    public void insertSimpleNoteEtudiants(int idEtudiants, int idMatiere, int idSemestre, double note, Connection con) throws SQLException
    {
        PreparedStatement pstmt = con.prepareStatement("insert into notes (id_etudiants, id_matieres, id_semestres, notes)values(?, ?, ?, ?)");
        pstmt.setInt(1, idEtudiants);
        pstmt.setInt(2, idMatiere);
        pstmt.setInt(3, idSemestre);
        pstmt.setDouble(4, note);
        pstmt.executeUpdate();
        con.commit();
    }       
    
    public Matiere[] getAllMatiere(String classy, Connection con)
    {
        Matiere matiere = new Matiere();
        matiere.setClassy(classy);
        Object[] tabObject = matiere.findObject(con);
        Matiere[] tabMatiere = new Matiere[tabObject.length];
        for(int i = 0; i < tabObject.length; i++)
        {
            tabMatiere[i] = (Matiere) tabObject[i];
        }
        return tabMatiere;
    }
    
    public Etudiants getSimpleEtudiants(String etu, Connection con)
    {
        Etudiants etudiants = new Etudiants();
        etudiants.setEtu(etu);
        Object[] tabObject = etudiants.findObject(con);
        Etudiants etudiants2 = null;
        for(int i = 0; i < tabObject.length; i++)
        {
            etudiants2 = (Etudiants) tabObject[i];
        }
        return etudiants2;
    }
    
    public Classy getSimpleClassy(String classes, Connection con)
    {
        Classy classy = new Classy(classes);
        Object[] tabObject = classy.findObject(con);
        Classy classy2 = null;
        for(int i = 0; i < tabObject.length; i++)
        {
            classy2 = (Classy) tabObject[i];
        }
        return classy2;
    }
}








