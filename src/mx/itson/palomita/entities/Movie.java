/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.palomita.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import mx.itson.palomita.persistence.MySQLConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author José Alvarez and Omar Castelan
 */
public class Movie {

private int movieId;    
private String movieName;
private String synopsisMovie;
private String movieDuration;
private String movieGenre;
private String movieClassification;
private String nameActor;


/*
* @pharam String filter
* Se define un método estático que recupera una lista de objetos Movie desde una base de datos MySQL, filtrando los resultados según el nombre de la película. 
* El método establece una conexión a la base de datos mediante la clase MySQLConnection y ejecuta una consulta SQL utilizando un filtro de nombre de película. 
* Los resultados de la consulta se procesan mediante un bucle while que crea objetos Movie y los añade a una lista. Cada objeto Movie se inicializa con los datos correspondientes obtenidos de la consulta, 
* como el identificador de la película, nombre, sinopsis, duración, género, nombre del actor y clasificación de la película. Cualquier error durante la ejecución se captura y se imprime en la consola. 
* La lista resultante, que contiene objetos Movie con información detallada de películas, se devuelve al llamador del método. 
*/
public static  List<Movie> getAll(String filter){
    List<Movie> movie = new ArrayList();
    try{
        Connection conection = MySQLConnection.get();
        PreparedStatement statement = conection.prepareStatement("SELECT * FROM movie WHERE movieName LIKE ?");
        statement.setString(1,"%"+ filter+"%");
        
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            Movie m = new Movie();
            m.setMovieId(resultSet.getInt(1));
            m.setMovieName(resultSet.getString(2));
            m.setSynopsisMovie(resultSet.getString(3));
            m.setMovieDuration(resultSet.getString(4));
            m.setMovieGenre(resultSet.getString(5));
            m.setNameActor(resultSet.getString(6));
            m.setMovieClassification(resultSet.getString(7));
            movie.add(m);
            
        }
    }catch(SQLException ex){
        System.out.println("Error: "+ ex.getMessage());
    }
    return movie;
}

/*
* pharam String name, String synopsis, String classification, String actor, String duration, String genre
* Se define un método para insertar información de una película en una base de datos MySQL.
* El método recibe como parámetros el nombre de la película, la sinopsis, la clasificación, el nombre del actor, la duración y el género. 
* Primero, establece una conexión a la base de datos mediante la clase MySQLConnection. Luego, ejecuta una consulta SQL de inserción que añade una nueva fila a la tabla "movie" con los valores proporcionados.
* El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de inserción. Si la inserción fue exitosa, la variable result se establece como true. 
* Pero, el manejo de excepciones es básico, limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código.
*/


 public boolean save(String name, String synopsis, String classification, String actor, String duration, String genre){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "INSERT INTO movie (movieName, synopsisMovie, movieDuration, movieGenre, nameActor, movieClassification) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement statement = conexion.prepareStatement(query);
    statement.setString(1, name);
    statement.setString(2, synopsis);
    statement.setString(3, duration);
    statement.setString(4, genre);
    statement.setString(5, actor);
    statement.setString(6, classification);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    /*
    * @pharam int id, String name, String synopsis, String duration, String genre, String actor, String classification
    * Se define un método para actualizar información de una película en una base de datos MySQL. 
    * El método toma como parámetros el identificador de la película (id), el nuevo nombre, sinopsis, duración, género, nombre del actor y clasificación. 
    * Primero, establece una conexión a la base de datos mediante la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de actualización que modifica la fila correspondiente en la tabla "movie" con los nuevos valores proporcionados. 
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de actualización. 
    * Si la actualización fue exitosa, la variable result se establece como true. Sin embargo, el manejo de excepciones es básico, 
    * limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código. 
    */

    public boolean update(int id, String name, String synopsis, String duration, String genre, String actor, String classification){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "UPDATE movie SET movieName = ?, synopsisMovie = ?, movieDuration = ?,movieGenre = ?, nameActor= ?, movieClassification= ? WHERE movieId = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    statement.setString(1, name);
    statement.setString(2, synopsis);
    statement.setString(3, duration);
    statement.setString(4, genre);
    statement.setString(5, actor);
    statement.setString(6, classification);
    statement.setInt(7, id);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
    
    /*
    * @pharam int Id
    * Se define un método para eliminar una entrada de información de una película en una base de datos MySQL. 
    * El método toma como parámetro el identificador de la película (id). Primero, establece una conexión a la base de datos mediante la clase MySQLConnection. 
    * Luego, ejecuta una consulta SQL de eliminación que borra la fila correspondiente en la tabla "movie" basándose en el identificador proporcionado. 
    * El resultado de la operación se determina verificando si exactamente una fila fue afectada por la consulta de eliminación. Si la eliminación fue exitosa, la variable result se establece como true. 
    * Pero, el manejo de excepciones es básico, limitándose a imprimir mensajes de error en la consola en caso de cualquier problema durante la ejecución del código. 
    *
    */
    public boolean delete(int id){
    boolean result = false;
    try{
    Connection conexion = MySQLConnection.get();
    String query = "DELETE FROM movie WHERE movieId = ?";
    PreparedStatement statement = conexion.prepareStatement(query);
    
    statement.setInt(1, id);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSynopsisMovie() {
        return synopsisMovie;
    }

    public void setSynopsisMovie(String synopsisMovie) {
        this.synopsisMovie = synopsisMovie;
    }

    public String getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(String movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieClassification() {
        return movieClassification;
    }

    public void setMovieClassification(String movieClassification) {
        this.movieClassification = movieClassification;
    }

    public String getNameActor() {
        return nameActor;
    }

    public void setNameActor(String nameActor) {
        this.nameActor = nameActor;
    }

    
}

