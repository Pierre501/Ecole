<%-- 
    Document   : detailsResulat
    Created on : 16 mars 2022, 19:03:24
    Author     : Emmanuel
--%>

<%@page import="model.Semestres"%>
<%@page import="model.ResultatFinal"%>
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
            ResultatFinal tabResultatFinal = (ResultatFinal) model.getData().get("resultatFinal");
            Semestres[] tabSemestres = (Semestres[]) model.getData().get("tabSemestres");
        %>
        <h1>Détails résultat final</h1>
            <p><b>Etu : </b><% out.print(tabResultatFinal.getNotes()[0].getViewEtudiants().getEtu()); %></p>
            <p><b>Nom : </b><% out.print(tabResultatFinal.getNotes()[0].getViewEtudiants().getNom_etudiants()); %></p>
            <p><b>Prénom : </b><% out.print(tabResultatFinal.getNotes()[0].getViewEtudiants().getPrenom_etudiants()); %></p>
            <p><b>Né(e) le : </b><% out.print(tabResultatFinal.getNotes()[0].getViewEtudiants().getDate_de_naissance()); %> à <% out.print(tabResultatFinal.getNotes()[0].getViewEtudiants().getLieu_de_naissance()); %></p>
            <p><b>Inscrit :</b> en <% out.print(tabResultatFinal.getNotes()[0].getViewMatieres()[tabResultatFinal.getNotes()[0].getViewMatieres().length-1].getClassy()); %></p>
            <% for(int j = 0; j < tabResultatFinal.getNotes().length; j++) { %>
            <p><b>Résultat <% out.print(tabSemestres[j].getSemestres()); %></b></p>
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <th>Matière</th>
                    <th>Note</th> 
                    <th>Mention</th>
                </tr>
                <% for(int k = 0; k < tabResultatFinal.getNotes()[j].getViewMatieres().length; k++) { %>
                <tr>
                    <td><% out.print(tabResultatFinal.getNotes()[j].getViewMatieres()[k].getNom_matieres()); %></td>
                    <td><% out.print(tabResultatFinal.getNotes()[j].getViewMatieres()[k].getNotes()); %></td>
                    <td><% out.print(tabResultatFinal.getNotes()[j].getViewMatieres()[k].mention(tabResultatFinal.getNotes()[j].getViewMatieres()[k].getNotes())); %></td>
                </tr>
                <% } %>
            </table>
            <p><b>Moyenne : </b><% out.print(tabResultatFinal.getNotes()[j].getMoyenne()); %></p>
            <p><b>Mention : </b><% out.print(tabResultatFinal.getNotes()[j].getMentions()); %></p>
            <% } %>
            <p><b>Moyenne général : </b><% out.print(tabResultatFinal.getMoyenneFianle()); %></p>
            <p><b>Mention général : </b><% out.print(tabResultatFinal.getMentionsFinale()); %></p>
            <p><b>Décision final : </b><% out.print(tabResultatFinal.getDecisinFinal()); %></p>
        <p><a href="http://localhost:8888/Ecole/listeClasses4.do"> >>Retour</a></p>
    </body>
</html>
