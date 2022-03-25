package model;

import java.sql.Connection;
import java.sql.DriverManager;


public class Connexion 
{
    public Connection connexion()
    {
	Connection con = null;
	try
	{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gestion_de_ecole", "admin", "admin");
            con.setAutoCommit(false);
	}
	catch(Exception ex)
	{
            ex.printStackTrace();
	}
	return con;
    }

    public void deconnexion(Connection con)
    {
	try
	{
            con.close();
	}
	catch(Exception ex)
	{
            ex.printStackTrace();
	}
    }
}
