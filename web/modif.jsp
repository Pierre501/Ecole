<%-- 
    Document   : modif
    Created on : 15 mars 2022, 11:39:11
    Author     : Emmanuel
--%>

<%@page import="model.ViewEtudiants"%>
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
            ViewEtudiants etudiants = (ViewEtudiants) model.getData().get("viewEtudiants");
        %>
        <h1>Modification info etudiants</h1>
        <form action="http://localhost:8888/Ecole/modif.do" method="post">
            <p><input type="hidden" name="viewEtudiants.id_etudiants" value="<% out.print(etudiants.getId_etudiants()); %>"/></p>
            <p><input type="hidden" name="viewEtudiants.id_classy" value="<% out.print(etudiants.getId_classy()); %>"/></p>
            <p>Etu <input type="text" name="viewEtudiants.etu" value="<% out.print(etudiants.getEtu()); %>"/></p>
            <p>Nom <input type="text" name="viewEtudiants.nom_etudiants" value="<% out.print(etudiants.getNom_etudiants()); %>"/></p>
            <p>Prénom <input type="text" name="viewEtudiants.prenom_etudiants" value="<% out.print(etudiants.getPrenom_etudiants()); %>"/></p>
            <p>Date et lieu de naissance <input type="date" name="viewEtudiants.date_de_naissance" value="<% out.print(etudiants.getDate_de_naissance()); %>"/> à <input type="text" name="viewEtudiants.lieu_de_naissance" value="<% out.print(etudiants.getLieu_de_naissance()); %>"/></p>
            <p>Genre <input type="text" name="viewEtudiants.genre" value="<% out.print(etudiants.getGenre()); %>"/></p>
            <p>Adresse <input type="text" name="viewEtudiants.adresse" value="<% out.print(etudiants.getAdresse()); %>"/></p>
            <p>Classe <input type="text" name="viewEtudiants.classy" value="<% out.print(etudiants.getClassy()); %>"/></p>
            <p>Photo <input type="file" name="viewEtudiants.photo_etudiants" /></p>
            <p><input type="submit" value="Modifier" /></p>
        </form>
        <p><a href="http://localhost:8888/Ecole/listeClasses2.do"> >>Retour</a></p>
    </body>
</html>
