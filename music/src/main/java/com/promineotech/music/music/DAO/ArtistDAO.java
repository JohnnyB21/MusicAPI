package com.promineotech.music.music.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
// import java.util.HashMap;
// import java.util.LinkedList;
import java.util.List;
//import java.util.Map;

import com.promineotech.music.music.Entities.Artists;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

//import lombok.extern.slf4j.Slf4j;

//ArtistDAO complete with CRUD functions
//@Slf4j
@Service
public class ArtistDAO {
    // @Autowired
    // private NamedParameterJdbcTemplate jdbcTemplate;

    // public List<Artists> listAllArtists(List<String> artistID) {
    //     if(artistID.isEmpty()){
    //         return new LinkedList<>();
    //     }
    //     Map<String, Object> params = new HashMap<>();
    //     // @formatter:off
    //     String sql = ""
    //         + "SELECT * "
    //         + "FROM artists "
    //         + "WHERE id IN(";
    //     // @formatter:on

    //     for( int index = 0; index < artistID.size(); index++) {
    //         String key = "artist_" + index;
    //         sql += ":" + key + ", ";
    //         params.put(key, artistID.get(index));
    //     }
    //     sql = sql.substring(0, sql.length() - 2);
    //     sql += ")";
    //     return jdbcTemplate.query(sql, params, new RowMapper<Artists>() {
    //         @Override
    //         public Artists mapRow(ResultSet rs, int rowNum) throws SQLException {
    //             // @formatter:off
    //             return Artists.builder()
    //             .artistID(rs.getInt("id"))
    //             .firstName(rs.getString("first_name"))
    //             .lastName(rs.getString("last_name"))
    //             .age(rs.getInt("age"))
    //             .build();
    //             // @formatter:on
    //         }
            
    //     });
    // }
    // public List<Artists> findAllArtists(){    Better version?
    //     String sql = "SELECT * FROM artists";
    //     List<Artists> artists = jdbcTemplate.query(sql, 
    //     new ArtistRowMapper());

    //     return artists;
    // }

    // public class ArtistRowMapper implements RowMapper<Artists>{

    //     @Override
    //     public Artists mapRow(ResultSet rs, int rowNum) throws SQLException {
    //        Artists artist = new Artists();
    //        artist.setArtistID(rs.getInt("id"));
    //        artist.setFirstName(rs.getString("first_name"));
    //        artist.setLastName(rs.getString("last_name"));
    //        artist.setAge(rs.getInt("age"));

    //        return artist;
    //     }

    // }

    private static final String HOSTNAME = "jdbc:mysql://localhost:3306/music";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "100Gunter!";

    public static List<Artists> listAllArtists() {
        final String sql = "SELECT * FROM artists";

        try(
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            List<Artists> list = new ArrayList<>();
            while (rs.next()) {
                int artistID = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int age = rs.getInt("age");
                Artists artist = new Artists(artistID, firstName, lastName, age);
                list.add(artist);

            } return list;
        } catch (SQLException e) {
            printSQLException(e);
            System.out.println("Test");
            return Collections.emptyList();
        }
        
    }
    public static void newArtist(String firstName, String lastName, int age){
        final String sql = "INSERT INTO artists(first_name, last_name, age) VALUES (?,?,?);";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void updateArtist(int artistID, String firstName, String lastName, int age){
        final String sql = "UPDATE artists SET first_name = ?, last_name = ?, age = ? WHERE id = ?;";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, artistID);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            printSQLException(e);
        }
    }

    public static void deleteArtist (int artistID){
        final String sql = "DELETE FROM artists WHERE id = ?;";

        try (
            Connection connection = DriverManager.getConnection(HOSTNAME, USERNAME, PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, artistID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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
