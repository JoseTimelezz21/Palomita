/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.entities;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import mx.itson.palomita.persistence.MySQLConnection;

/**
 *
 * @author José Alvarez and Omar Castelan
 */
public class Billboard {

private int idFunction;
private Movie movie;
private Hall hall;
private String date;

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
      
    /*
    * @pharam String filter 
    * El método getAll realiza una consulta a una tabla llamada "palomitafunction" en una base de datos, utilizando un filtro proporcionado. Luego,
    * crea una lista de objetos "Billboard" con la información obtenida de la consulta. Para cada fila recuperada, se crea un objeto "Billboard" al cual se le asignan propiedades como la fecha, el ID de la función,
    * y se busca información adicional en las tablas "hall" y "movie" para asignar a las propiedades relacionadas. Finalmente, los objetos "Billboard" se agregan a una lista, que se devuelve como resultado del método.
    * Se manejan excepciones en caso de errores durante la ejecución de la consulta.
    */
    
    public static  List<Billboard> getAll(String filter){
    List<Billboard> billboard = new ArrayList();
    try{
        Connection conection = MySQLConnection.get();
        PreparedStatement statement = conection.prepareStatement("SELECT * FROM palomitafunction WHERE idHall LIKE ?");
        statement.setString(1,"%"+ filter +"%");
        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            Billboard billboard2 = new Billboard();
            List<Hall> hall = new ArrayList();
            hall = Hall.getAll("");
            List<Movie> movie = new ArrayList();
            movie= Movie.getAll("");
        for(Hall h : hall){
            resultSet.getInt(4);
            if(resultSet.getInt(4) == h.getIdHall()){
                billboard2.setHall(h);
            }
        }
        for(Movie m : movie){
            resultSet.getInt(3);
            if(resultSet.getInt(3) == m.getMovieId()){
                billboard2.setMovie(m);
            }
        }
        billboard2.setDate(resultSet.getString(2));
        billboard2.setIdFunction(resultSet.getInt(1));
         billboard.add(billboard2);
           
        }
    }catch(SQLException ex){
        System.out.println("Error: "+ ex.getMessage());
    }
    return billboard;
    
    }
    
    /*
    * @pharam int movieId, int idHall, String date
    * Se define un método para almacenar información sobre funciones de cine en una base de datos MySQL.
    * Recibe como parámetros el identificador de la película, el de la sala de cine y la fecha de la función.
    * La conexión a la base de datos se establece a través de la clase MySQLConnection, 
    * y luego se ejecuta una consulta SQL de inserción para agregar una nueva entrada a la tabla "palomitafunction" con los valores proporcionados.
    * El método devuelve un booleano que indica si la operación de inserción fue exitosa. 
    */
    public boolean save( int movieId, int idHall, String date){
       boolean result = false;
       try{
           Connection conexion = MySQLConnection.get();
    String query = "INSERT INTO palomitafunction (movieId, idHall, date) VALUES (?, ?, ?)";
    PreparedStatement statement = conexion.prepareStatement(query);
    statement.setInt(1, movieId);
    statement.setInt(2, idHall);
    statement.setString(3, date);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    
       }catch(Exception ex){
           
       }
    return result;
   
    }
    
    /*
    * @pharam int idFunction, String date, int movieId, int idHall
    * Se define un método para actualizar información de funciones de cine en una base de datos MySQL. 
    * El método recibe como parámetros el identificador de la función, la nueva fecha, el nuevo identificador de película y el nuevo identificador de sala de cine. 
    * Primero, establece una conexión a la base de datos a través de la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de actualización que modifica la fila correspondiente en la tabla "palomitafunction" con los nuevos valores proporcionados.
    * El método devuelve un booleano indicando si la operación de actualización fue exitosa, verificando si exactamente una fila fue afectada por la consulta. 
    */
    
    public boolean update(int idFunction, String date, int movieId, int idHall) {
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "UPDATE palomitafunction SET movieId = ?, idHall = ?, date=? WHERE funcionId = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(4, idFunction);
    statement.setString(3, date);
    statement.setInt(1, movieId);
    statement.setInt(2, idHall);
    
    
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    /*
    * @pharam int idFunction
    * Se define un método para eliminar una entrada de funciones de cine en una base de datos MySQL.
    * El método toma como parámetro el identificador de la función que se desea eliminar. Primero, establece una conexión a la base de datos utilizando la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de eliminación que elimina la fila correspondiente en la tabla "palomitafunction" basándose en el identificador proporcionado.
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de eliminación.
    */
    public boolean delete(int idFunction){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "DELETE FROM palomitafunction WHERE idFunction = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(1,  idFunction);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }  
        
}

