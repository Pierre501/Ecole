<%-- 
    Document   : index
    Created on : 12 mars 2022, 15:12:00
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
        <h1>Login Professeurs</h1>
        <form action="http://localhost:8888/Ecole/login.do" method="POST">
            <p>Nom d'utilisateur <input type="text" name="professeurs.username" /></p>
            <p>Mot de passe <input type="password" name="professeurs.mot_de_passe" /></p>
            <p><input type="submit" value="Connexion" /></p>
        </form>
    </body>
</html>
