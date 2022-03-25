<%-- 
    Document   : erreur
    Created on : 14 mars 2022, 05:22:12
    Author     : USER
--%>

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
            String erreur = (String) model.getData().get("erreur");
        %>
        <h1>Login Professeurs</h1>
        <form action="http://localhost:8888/Ecole/login.do" method="POST">
            <p>Nom d'utilisateur <input type="text" name="professeurs.username" /></p>
            <p>Mot de passe <input type="password" name="professeurs.mot_de_passe" /></p>
            <p><input type="submit" value="Connexion" /></p>
            <p><% out.print(erreur); %></p>
        </form>
    </body>
</html>
