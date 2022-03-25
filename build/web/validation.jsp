<%-- 
    Document   : validation
    Created on : 18 mars 2022, 17:48:32
    Author     : Emmanuel
--%>

<%@page import="model.Validation_notes"%>
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
            Validation_notes[] tabValidation = (Validation_notes[]) model.getData().get("tabValidation");
        %>
        <h1>Liste des notes</h1>
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <th>Etu</th>
                <th>Matière</th>
                <th>Note</th>
                <th>Semestre</th>
                <th>Options</th>
            </tr>
        <% for(int i = 0; i < tabValidation.length; i++) { %>
            <tr>
                <td><% out.print(tabValidation[i].getEtu()); %></td>
                <td><% out.print(tabValidation[i].getNom_matieres()); %></td>
                <td><% out.print(tabValidation[i].getNotes()); %></td>
                <td><% out.print(tabValidation[i].getSemestres()); %></td>
                <td><a href="http://localhost:8888/Ecole/validation.do?validation_notes.id_validation_notes=<% out.print(tabValidation[i].getId_validation_notes()); %>">Valider</a></td>
            </tr>
        <% } %>
        </table>
        <p><a href="chefs.jsp"> >>Retour</a></p>
    </body>
</html>
