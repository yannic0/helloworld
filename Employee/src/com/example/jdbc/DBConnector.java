package com.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector
{

    private static Connection con = null;
    
	// HIER DEN CONNECT STRING ANPASSEN //
    private String connectString = "jdbc:mysql://localhost/testdb?user=root&password=&serverTimezone=UTC";  /* CHANGE */
    
    private Connection openConnection()
    {
		try
		{
   			Class.forName( "com.mysql.cj.jdbc.Driver" );
    		con = DriverManager.getConnection( connectString ); 
    		//con.setAutoCommit(true);
    		return con;
		}
		catch( Exception e)
		{
    		System.out.println( "No connection to " + connectString );
    		e.printStackTrace();
		}
		return null;
    }
    
    public static Connection getConnection()
    {
        if( con == null )
        {
            return new DBConnector().openConnection();
        }
        return con;
    }
    
    public static void closeConnection()
    {
        try
        {
            getConnection().close();
            con = null;
        }
        catch( Exception e )
        {
            System.out.println( "CLOSE CONNECTION: " + e.getMessage() );
        }
    }    
    
}

