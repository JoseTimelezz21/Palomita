/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.filter;
import mx.itson.palomita.persistence.MySQLConnection;

/**
 *
 * @author José Alvarez and Omar Castelan
 */
public class Hall {
    private int idHall;
    private String availability;    
    private String hallNumber;
    
    
    public static  List<Hall> gatAll(String filter){
    List<Hall> hall = new ArrayList();
    try{
        Connection conection = MySQLConnection.get();
        PreparedStatement statement = conection.prepareStatement("SELECT * FROM hall WHERE hallNumber LIKE ?");
        statement.setString(1,"%"+ filter+"%");
        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            Hall h = new Hall();
            h.setIdHall(resultSet.getInt(1));
            h.setAvailability(resultSet.getString(2));
            h.setHallNumber(resultSet.getString(3));
            hall.add(h);
        }
    }catch(SQLException ex){
        System.out.println("Error: "+ ex.getMessage());
    }
    return hall;
}
    public boolean save(String availability, String hallNumber){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "INSERT INTO hall (availability, hallNumber) VALUES (?, ?)";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    
    statement.setString(1, availability);
    statement.setString(2, hallNumber);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    public boolean update(int idHall, String availability, String hallNumber){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "UPDATE hall SET availability = ?, hallNumber = ? WHERE idHall = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setString(1, availability);
    statement.setString(2, hallNumber);
    statement.setInt(3, idHall);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    
    public boolean delete(int idHall){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "DELETE FROM hall WHERE idHall = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(1, idHall);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    
    public int getIdHall() {
        return idHall;
    }

    public void setIdHall(int idHall) {
        this.idHall = idHall;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability( String availability) {
        this.availability = availability;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }
}
