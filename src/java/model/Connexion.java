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
            con = DriverManager.getConnection("jdbc:postgresql://ec2-3-225-213-67.compute-1.amazonaws.com:5432/d8euvg72fl0tjq", "liqwwjgrghnumg", "8305d4e4c51ca5e427fa097d060275bdfa877b8ebb45fd043429a3e679bc0272");
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
