<%-- 
    Document   : ajouter
    Created on : 15 mars 2022, 12:39:08
    Author     : Emmanuel
--%>

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
        <h1>Ajouter une etudiants</h1>
        <% 
            ModelView model = (ModelView) request.getAttribute("data");
            Classy[] tabClasses = (Classy[]) model.getData().get("classes");
        %>
        <form action="http://localhost:8888/Ecole/ajouter.do" method="post">
            <p><b>Etu </b><input type="text" name="viewEtudiants.etu" /></p>
            <p><b>Nom </b><input type="text" name="viewEtudiants.nom_etudiants" /> <b>et Prénom </b><input type="text" name="viewEtudiants.prenom_etudiants" /></p>
            <p><b>Date et lieu de naissance </b><input type="date" name="viewEtudiants.date_de_naissance"> <input type="text" name="viewEtudiants.lieu_de_naissance" /></p>
            <p><b>Genre</b> 
                <select name="viewEtudiants.genre">
                    <option value="null">Choisir votre genre</option>
                    <option value="Hommes">Hommes</option>
                    <option value="Femmes">Femmes</option>
                </select>
            </p>
            <p><b>Adresse <b><input type="text" name="viewEtudiants.adresse" /></p>
            <p><b>Photo </b><input type="file" name="viewEtudiants.photo_etudiants" /></p>
            <p><b>Liste des classes</b>
                <select name="viewEtudiants.classy">
                    <option value="null">Choisir une classe</option>
                <% for(int i = 0; i < tabClasses.length; i++) { %>
                    <option value="<% out.print(tabClasses[i].getClassy()); %>"><% out.print(tabClasses[i].getClassy()); %></option>
                <% } %>
                </select>
            </p>
            <p><input type="submit" value="Ajouter" /></p>
        </form>
        <p><a href="http://localhost:8888/Ecole/listeClasses2.do"> >>Retour</a></p>
    </body>
</html>
