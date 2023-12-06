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
    
    /*
    * @pharam String filter
    * Se define un método estático que recupera una lista de objetos Hall desde una base de datos MySQL, filtrando los resultados según un número de sala proporcionado. 
    * El método establece una conexión a la base de datos mediante la clase MySQLConnection y ejecuta una consulta SQL utilizando un filtro de número de sala. 
    * Los resultados de la consulta se procesan mediante un bucle while que crea objetos Hall y los añade a una lista. Cualquier error durante la ejecución se captura y se imprime en la consola.
    * La lista resultante, que contiene objetos Hall con información de salas de cine, se devuelve al llamador del método. 
    */
    public static  List<Hall> getAll(String filter){
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
    
    /*
    * @pharam String availability, String hallNumber
    * El método recibe como parámetros la disponibilidad y el número de la sala. Primero, establece una conexión a la base de datos utilizando la clase MySQLConnection.
    * Luego, ejecuta una consulta SQL de inserción que añade una nueva fila a la tabla "hall" con los valores proporcionados.
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de inserción.
    * Si la inserción fue exitosa, la variable result se establece como true. Sin embargo, el manejo de excepciones es básico,
    * limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código. 
    */
    
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
    
    /*
    * @pharam int idHall, String availability, String hallNumber
    * Se define un método para actualizar la información de una sala de cine en una base de datos MySQL. 
    * El método toma como parámetros el identificador de la sala (idHall), la nueva disponibilidad y el nuevo número de sala. 
    * Primero, establece una conexión a la base de datos utilizando la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de actualización que modifica la fila correspondiente en la tabla "hall" con los nuevos valores proporcionados.
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de actualización. 
    * Si la actualización fue exitosa, la variable result se establece como true. Pero, el manejo de excepciones es básico, 
    * limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código. 
    *
    */
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
    
    /*
    * @pharam int idHall
    * método para eliminar una entrada de información de una sala de cine en una base de datos MySQL. 
    * El método toma como parámetro el identificador de la sala (idHall). Primero, establece una conexión a la base de datos mediante la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de eliminación que borra la fila correspondiente en la tabla "hall" basándose en el identificador proporcionado. 
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de eliminación. Si la eliminación fue exitosa, la variable result se establece como true. 
    * No obstante, el manejo de excepciones es básico, limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código.
    */
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
