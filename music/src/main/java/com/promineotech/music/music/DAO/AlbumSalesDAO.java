package com.promineotech.music.music.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.promineotech.music.music.Entities.AlbumSales;
import org.springframework.stereotype.Service;

//AlbumSalesDAO with complete CRUD functions

@Service
public class AlbumSalesDAO {
    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "100Gunter!";

    public static List<AlbumSales> getAlbumSales(){
        final String sql = "SELECT * FROM album_sales";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            ResultSet rs = preparedStatement.executeQuery();
            List<AlbumSales> list = new ArrayList<>();
            while (rs.next()) {
                int albumSalesID = rs.getInt("id");
                int albumID = rs.getInt("album_id");
                int albumsSold = rs.getInt("albums_sold");
                AlbumSales sale = new AlbumSales(albumSalesID, albumID, albumsSold);
                list.add(sale);

            } return list;
        } catch (SQLException e){
            printSQLException(e);
            System.out.println("Test");
            return Collections.emptyList();
        }
    }

    public static void newAlbumSale(int albumID, int albumsSold){
        final String sql = "INSERT INTO album_sales(album_id, albums_sold) VALUES (?, ?);";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, albumID);
            preparedStatement.setInt(2, albumsSold);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void updateAlbumSale(int albumSalesID, int albumID, int albumsSold){
        final String sql = "UPDATE album_sales SET album_id = ?, albums_sold = ? WHERE id = ?;";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, albumID);
            preparedStatement.setInt(2, albumsSold);
            preparedStatement.setInt(3, albumSalesID);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void deleteAlbumSales(int albumSalesID){
        final String sql = "DELETE FROM album_sales WHERE id = ?;";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, albumSalesID);

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
