

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.*;
import java.lang.*;

import net.sourceforge.jtds.jdbc.*;
import net.sourceforge.jtds.jdbc.cache.*;
import net.sourceforge.jtds.jdbcx.*;
import net.sourceforge.jtds.jdbcx.proxy.*;
import net.sourceforge.jtds.ssl.*;
import net.sourceforge.jtds.util.*;

public class TestJDBCDriver {
 
  public static void main(String[] args) {

	//test Microsoft JDBC driver
	//String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//test jtds SQL server driver
	String driverName = "net.sourceforge.jtds.jdbc.Driver";
	// Create a connection to the database
	String serverName = "localhost";
	String serverPort = "1433";
	String database = serverName + ":" + serverPort;
	//String url = "jdbc:sqlserver://localhost:1433;databaseName=James;encrypt=true;trustServerCertificate=true";
	String url =  "jdbc:jtds:sqlserver://localhost:1433/hMailServerDB;TDS=7.0";
	String username = "jamesuser";
	String password = "jamesuser";
    try {
    	Class.forName(driverName);
    	System.out.println("Successfully Connected to the database!");
    	System.out.println(System.getProperty("java.ext.dirs"));   	
    } catch (ClassNotFoundException e) {
    	System.out.println("Could not find the database driver " + e.getMessage());
    }
    System.out.println("Start to connect to hMailServerDB");
    
    Connection connection = null;
    try{
    	connection = DriverManager.getConnection(url, username, password);
    	System.out.println("successfully connected to the hMailServerDB");
    } catch (SQLException e) {
    	System.out.println("Could not connect to the database " + e.getMessage());
    }
    //select all table data from user table
    try{
        String sqlStr = "select [hMailServerDB].[dbo].[hm_accounts].[accountaddress] from [hMailServerDB].[dbo].[hm_accounts]";
        System.out.println("sql is:" + sqlStr);
        Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,  ResultSet.CONCUR_READ_ONLY );
        ResultSet rs = statement.executeQuery(sqlStr);
        while( rs.next() ){
        	System.out.println(rs.getString(1));
        }
        rs.close() ;
        statement.close() ;
        connection.close() ;            	
    }catch(SQLException e){
    	System.out.println("fail:" + e.getMessage());
    }
    
	System.out.println("done");
    
 }

}