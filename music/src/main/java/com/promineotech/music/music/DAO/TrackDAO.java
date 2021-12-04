package com.promineotech.music.music.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.promineotech.music.music.Entities.Tracks;

import org.springframework.stereotype.Service;

@Service
public class TrackDAO {
    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456P";

    public static List<Tracks> getTracks(){
        final String sql = "SELECT * FROM tracks";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql); 
        ){
            ResultSet rs = preparedStatement.executeQuery();
            List<Tracks> list = new ArrayList<>();
            while (rs.next()){
                int trackID = rs.getInt("id");
                String trackName = rs.getString("track_name");
                int albumID = rs.getInt("album_id");
                Tracks track = new Tracks(trackID, trackName, albumID);
                list.add(track);

            } return list;
        } catch (SQLException e){
            printSQLException(e);
            System.out.println("Test");
            return Collections.emptyList();
        }
    }

    public static void newTrack(String trackName, int albumID){
        final String sql = "INSERT INTO tracks(track_name, album_id) VALUES (?, ?);";
        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql); 
        ){
            preparedStatement.setString(1, trackName);
            preparedStatement.setInt(2, albumID);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void updateTrack(int trackID, String trackName, int albumID){
        final String sql = "UPDATE tracks SET track_name = ?, album_id = ? WHERE id = ?;";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql); 
        ){
            preparedStatement.setString(1, trackName);
            preparedStatement.setInt(2, albumID);
            preparedStatement.setInt(3, trackID);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void deleteTrack(int trackID){
        final String sql = "DELETE FROM  tracks WHERE id = ?;";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement =connection.prepareStatement(sql); 
        ){
            preparedStatement.setInt(1, trackID);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
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
