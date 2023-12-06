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

public static  List<Movie> gatAll(String filter){
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
  


 public boolean save(String name, String synopsis, String clasification, String actor, String duration, String genre){
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
    statement.setString(6, clasification);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }

    public boolean update(int id, String name, String synopsis, String duration, String genre, String actor, String clasification){
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
    statement.setString(6, clasification);
    statement.setInt(7, id);
    statement.execute();
    
    result = statement.getUpdateCount() == 1;
    
    conexion.close();
    }catch(Exception ex){
    System.err.println("Error: " + ex.getMessage());
    
    }
    return result;
    }
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

