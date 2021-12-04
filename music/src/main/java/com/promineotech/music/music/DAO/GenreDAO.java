package com.promineotech.music.music.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.promineotech.music.music.Entities.Genres;

import org.springframework.stereotype.Service;

@Service
public class GenreDAO {
    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "100Gunter!";

    public static List<Genres> getGenres(){
        final String sql = "SELECT * FROM genres";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql); 
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            List<Genres> list = new ArrayList<>();
            while (rs.next()) {
                int genreID = rs.getInt("id");
                String genreName = rs.getString("genre_name");
                Genres genre = new Genres(genreID, genreName);
                list.add(genre);

            } return list;
        } catch (SQLException e){
            printSQLException(e);
            System.out.println("Test");
            return Collections.emptyList();
        }
    }
    
    public static void newGenre(String genreName) {
        final String sql = "INSERT INTO genres(genre_name) VALUES (?);";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql); 
        ){
            preparedStatement.setString(1, genreName);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void updateGenre(int genreID, String genreName){
        final String sql = "UPDATE genres SET genre_name = ? WHERE id = ?;";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, genreName);
            preparedStatement.setInt(2, genreID);

            preparedStatement.executeUpdate();


        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void deleteGenre(int genreID){
        final String sql = "DELETE FROM genres WHERE id = ?;";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, genreID);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }



    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
    }
}
