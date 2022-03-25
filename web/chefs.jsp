<%-- 
    Document   : chefs
    Created on : 18 mars 2022, 17:07:19
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login directeur</h1>
        <form action="http://localhost:8888/Ecole/loginDirecteur.do" method="post">
            <p><b>Nom d'utilisateur</b> <input type="text" name="directeur.username" /></p>
            <p><b>Mot de passe</b> <input type="password" name="directeur.motDePasse" /></p>
            <p><input type="submit" value="Connexion" /></p>
        </form>
        <p><a href="accueil.jsp"> >>Retour</a></p>
    </body>
</html>
