<%-- 
    Document   : bulletin
    Created on : 14 mars 2022, 15:22:11
    Author     : Emmanuel
--%>

<%@page import="model.ViewEtudiants"%>
<%@page import="model.Notes"%>
<%@page import="model.Semestres"%>
<%@page import="model.Classy"%>
<%@page import="model.ModelView"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            ModelView model = (ModelView) request.getAttribute("data");
            Semestres[] tabSemestres = (Semestres[]) model.getData().get("tabSemestres");
            Notes notes = (Notes) model.getData().get("notes");
            ViewEtudiants etudiants = (ViewEtudiants) model.getData().get("etudiant");
        %>
        <h1>Recherche bulletin</h1>
        <form action="http://localhost:8888/Ecole/notes.do" method="post">
            <p><b>Etu </b><input type="text" name="viewMatieres.etu" /></p>
            <p><b>Liste des semestres</b>
                <select name="viewMatieres.semestres">
                    <option value="null">Choisir une semestre</option>
                <% for(int i = 0; i < tabSemestres.length; i++) { %> 
                    <option value="<% out.print(tabSemestres[i].getSemestres()); %>"><% out.print(tabSemestres[i].getSemestres()); %></option>
                <% } %>
                </select>
            </p>
            <p><input type="submit" value="Valider" /></p>
        </form>
        <% if(notes != null) { %>
            <h1>Réleve des notes</h1>
            <p><b>Etu : </b><% out.print(notes.getViewEtudiants().getEtu()); %></p>
            <p><b>Nom : </b><% out.print(notes.getViewEtudiants().getNom_etudiants()); %></p>
            <p><b>Prénom : </b><% out.print(notes.getViewEtudiants().getPrenom_etudiants()); %></p>
            <p><b>Né(e) le : </b><% out.print(notes.getViewEtudiants().getDate_de_naissance()); %> à <% out.print(notes.getViewEtudiants().getLieu_de_naissance()); %></p>
            <p><b>Inscrit :</b> en <% out.print(etudiants.getClassy()); %></p>
            <p><b>Résultat <% out.print(notes.getViewMatieres()[0].getSemestres()); %></b></p>
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <th>Matière</th>
                    <th>Note</th> 
                    <th>Mention</th>
                </tr>
            <% for(int i = 0; i < notes.getViewMatieres().length; i++) { %>  
                <tr>
                    <td><% out.print(notes.getViewMatieres()[i].getNom_matieres()); %></td>
                    <td><% out.print(notes.getViewMatieres()[i].getNotes()); %></td>
                    <td><% out.print(notes.getViewMatieres()[i].mention(notes.getViewMatieres()[i].getNotes())); %></td>
                </tr>
            <% } %>
            </table>
            <p><b>Moyenne : </b><% out.print(notes.getMoyenne()); %></p>
            <p><b>Mention : </b><% out.print(notes.getMentions()); %></p>
        <% } %>
        <p><a href="accueil.jsp"> >>Retour</a></p>
    </body>
</html>
