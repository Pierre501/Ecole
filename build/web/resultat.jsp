<%-- 
    Document   : resultat
    Created on : 16 mars 2022, 13:16:45
    Author     : Emmanuel
--%>

<%@page import="model.ResultatFinal"%>
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
            Classy[] tabClasses = (Classy[]) model.getData().get("classes");
            ResultatFinal[] resultatAdmis = (ResultatFinal[]) model.getData().get("resultatAdmis");
            ResultatFinal[] resultatNonAdmis = (ResultatFinal[]) model.getData().get("resultatNonAdmis");
        %>
        <h1>Résultat fin d'année</h1>
        <form action="http://localhost:8888/Ecole/resultat.do" method="post">
            <p>Liste des classes 
                <select name="viewMatieres.classy">
                    <option value="null">Choisir une classe</option>
                <% for(int i = 0; i < tabClasses.length; i++) { %>
                    <option value="<% out.print(tabClasses[i].getClassy()); %>"><% out.print(tabClasses[i].getClassy()); %></option>
                <% } %>
                </select>
            </p>
            <p><input type="submit" value="Valider" /></p>
        </form>
        <% if(resultatAdmis != null || resultatNonAdmis != null) { %>
            <h1>Liste des etudiants adims</h1>
            <table border="1" cellpadding="0" cellSpacing="0">
                <tr>
                    <th>Etu</th>
                    <th>Nom et prénom</th>
                    <th>Mention</th>
                    <th>Décision</th>
                    <th>Options</th>
                </tr>
            <% for(int i = 0; i < resultatAdmis.length; i++) { %>
                <tr>
                    <td><% out.print(resultatAdmis[i].getEtu()); %></td>
                    <td><% out.print(resultatAdmis[i].getNotes()[0].getViewEtudiants().getNom_etudiants()); %> <% out.print(resultatAdmis[i].getNotes()[0].getViewEtudiants().getPrenom_etudiants()); %></td>
                    <td><% out.print(resultatAdmis[i].getMentionsFinale()); %></td>
                    <td><% out.print(resultatAdmis[i].getDecisinFinal()); %></td>
                    <td><a href="http://localhost:8888/Ecole/detailsResultat.do?viewMatieres.classy=<% out.print(resultatAdmis[i].getNotes()[0].getViewEtudiants().getClassy()); %>&viewMatieres.etu=<% out.print(resultatAdmis[i].getNotes()[0].getViewEtudiants().getEtu()); %>">Détails</a></td>
                </tr>
            <% } %>
            </table>
            <h1>Liste des etudiants non adims</h1>
            <table border="1" cellpadding="0" cellSpacing="0">
                <tr>
                    <th>Etu</th>
                    <th>Nom et prénom</th>
                    <th>Mention</th>
                    <th>Décision</th>
                    <th>Options</th>
                </tr>
            <% for(int i = 0; i < resultatNonAdmis.length; i++) { %>
                <tr>
                    <td><% out.print(resultatNonAdmis[i].getNotes()[0].getViewEtudiants().getEtu()); %></td>
                    <td><% out.print(resultatNonAdmis[i].getNotes()[0].getViewEtudiants().getNom_etudiants()); %> <% out.print(resultatNonAdmis[i].getNotes()[0].getViewEtudiants().getPrenom_etudiants()); %></td>
                    <td><% out.print(resultatNonAdmis[i].getMentionsFinale()); %></td>
                    <td><% out.print(resultatNonAdmis[i].getDecisinFinal()); %></td>
                    <td><a href="http://localhost:8888/Ecole/detailsResultat.do?viewMatieres.classy=<% out.print(resultatNonAdmis[i].getNotes()[0].getViewEtudiants().getClassy()); %>&viewMatieres.etu=<% out.print(resultatNonAdmis[i].getNotes()[0].getViewEtudiants().getEtu()); %>">Détails</a></td>
                </tr>
            <% } %>
            </table>
        <% } %>
        <p><a href="accueil.jsp"> >>Retour</a></p>
    </body>
</html>
