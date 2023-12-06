/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author José Alvarez and Omar Castelan
 */
public class MySQLConnection {
    public static Connection get(){
    Connection connection = null;
    try{
    //connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=&password=");
    connection = DriverManager.getConnection("jdbc:mysql://localhost/palomitadb?user=root&password=ahri21567");
    }catch(SQLException ex){
        System.err.print("Error: " + ex.getMessage());
    
    
    }
    return connection;
    
    }
}
