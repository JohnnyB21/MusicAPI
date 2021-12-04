package com.promineotech.music.music.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.promineotech.music.music.Entities.Albums;

import org.springframework.stereotype.Service;

//AlbumDAO with complete CRUD functions
@Service
public class AlbumDAO {
    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456P";

    public static List<Albums> getAlbums() {
        final String sql = "SELECT * FROM albums";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql);
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            List<Albums> list = new ArrayList<>();
            while (rs.next()) {
                int albumID = rs.getInt("id");
                String albumName = rs.getString("album_name");
                int artistID = rs.getInt("artist_id");
                int genreID = rs.getInt("genre_id");
                Albums album = new Albums(albumID, albumName, artistID, genreID);
                list.add(album);

            } return list;
        } catch (SQLException e) {
            printSQLException(e);
            System.out.println("Test");
            return Collections.emptyList();
        }
    }

    public static void newAlbum(String albumName, int artistID, int genreID){
        final String sql = "INSERT INTO albums(album_name, artist_id, genre_id) VALUES (?, ?, ?);";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, albumName);
            preparedStatement.setInt(2, artistID);
            preparedStatement.setInt(3, genreID);

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void updateAlbum(int albumID, String albumName, int artistID, int genreID){
        final String sql = "UPDATE albums SET album_name = ?, artist_id = ?, genre_id = ? WHERE id = ?;";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, albumName);
            preparedStatement.setInt(2, artistID);
            preparedStatement.setInt(3, genreID);
            preparedStatement.setInt(4, albumID);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void deleteAlbum(int albumID){
        final String sql = "DELETE FROM albums WHERE id = ?;";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, albumID);

            preparedStatement.executeUpdate();

        }catch (SQLException e) {
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
