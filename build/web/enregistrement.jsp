<%-- 
    Document   : enregistrement
    Created on : 18 mars 2022, 13:36:56
    Author     : Emmanuel
--%>

<%@page import="model.Matieres"%>
<%@page import="model.ModelView"%>
<%@page import="model.Semestres"%>
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
            Matieres[] tabMatieres = (Matieres[]) model.getData().get("tabMatieres");
        %>
        <h1>Enregistrement des notes</h1>
        <form action="http://localhost:8888/Ecole/enregistrer.do" method="post">
            <p><b>Etu </b><input type="text" name="viewMatieres.etu" /></p> 
            <p><b>Liste des matieres</b>
                <select name="viewMatieres.nom_matieres">
                    <option value="null">Choisir une matieres</option>
                    <% for(int i = 0; i < tabMatieres.length; i++) { %> 
                    <option value="<% out.print(tabMatieres[i].getNom_matieres()); %>"><% out.print(tabMatieres[i].getNom_matieres()); %></option>
                <% } %>
                </select>
            </p>
            <p><b>Notes</b> <input type="text" name="viewMatieres.notes" /></p>
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
        <p><a href="accueil.jsp"> >>Retour</a></p>
    </body>
</html>
