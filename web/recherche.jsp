<%-- 
    Document   : recherche
    Created on : 14 mars 2022, 13:01:56
    Author     : USER
--%>

<%@page import="model.ViewEtudiants"%>
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
        <h1>Page de recherche</h1>
        <% 
            ModelView model = (ModelView) request.getAttribute("data");
            Classy[] tabClasses = (Classy[]) model.getData().get("classes");
            ViewEtudiants[] tabEtudiants = (ViewEtudiants[]) model.getData().get("tabViewEtudiants");
        %>
        <form action="http://localhost:8888/Ecole/recherche.do" method="post">
            <p>Numéro etu <input type="text" name="viewEtudiants.etu" /></p>
            <p>Listes des classes
                <select name="viewEtudiants.classy">
                    <option value="null">Choisir une classe</option>
                <% for(int i = 0; i < tabClasses.length; i++) { %>
                    <option value="<% out.print(tabClasses[i].getClassy()); %>"><% out.print(tabClasses[i].getClassy()); %></option>
                <% } %>
                </select>
            </p>
            <p>Genre
                <select name="viewEtudiants.genre">
                    <option value="null">Choisir votre genre</option>
                    <option value="Hommes">Hommes</option>
                    <option value="Femmes">Femmes</option>
                </select>
            </p>
            <p><input type="submit" value="rechercher"</p>
        </form>
        <% if(tabEtudiants != null) { %>
            <h1>Détails de la recherche</h1>
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <th>Etu</th>
                    <th>Nom et prénom</th>
                    <th>Date et lieu de naissance</th>
                    <th>Genre</th>
                    <th>Adresse</th>
                    <th>Classe</th>
                </tr>
            <% for(int i = 0; i <tabEtudiants.length; i++) { %>
                <tr>
                    <td><% out.print(tabEtudiants[i].getEtu()); %></td>
                    <td><% out.print(tabEtudiants[i].getNom_etudiants()); %> <% out.print(tabEtudiants[i].getPrenom_etudiants()); %></td>
                    <td><% out.print(tabEtudiants[i].formatDate(tabEtudiants[i].getDate_de_naissance())); %> à <% out.print(tabEtudiants[i].getLieu_de_naissance()); %></td>
                    <td><% out.print(tabEtudiants[i].getGenre()); %></td>
                    <td><% out.print(tabEtudiants[i].getAdresse()); %></td>
                    <td><% out.print(tabEtudiants[i].getClassy()); %></td>
                </tr>
            <% } %>
            </table>
        <% } %>
        <p><a href="accueil.jsp"> >>Retour</a></p>
    </body>
</html>
