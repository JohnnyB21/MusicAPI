package com.promineotech.music.music.Service;

import java.util.List;
import com.promineotech.music.music.Entities.AlbumSales;
import com.promineotech.music.music.Entities.Albums;
import com.promineotech.music.music.Entities.Artists;
import com.promineotech.music.music.Entities.Genres;
import com.promineotech.music.music.Entities.Tracks;

public interface MusicService {

    /**
     * 
     * @return
     */
    List<Artists> getArtists();
    void updateArist(int artistID, String firstName, String lastName, int age);
    void newArtist(String firstName, String lastName, int age);
    void deleteArtist(int artistID);
    
    /**
     * 
     * @param albumID
     * @return
     */
    List<Albums> getAlbums(int albumID);
    void updateAlbum(int albumID, String albumName, int artistID, int genreID);
    void newAlbum(String albumName, int artistID, int genreID);
    void deleteAlbum(int albumID);

    /**
     * 
     * @param genreID
     * @return
     */
    List<Genres> getGenres(int genreID);
    void updateGenre(int genreID, String genreName);
    void newGenre(String genreName);
    void deleteGenre(int genreID);

    /**
     * 
     * @param trackID
     * @return
     */
    List<Tracks> getTracks(int trackID);
    void updateTrack(int trackID, String trackName, int albumID);
    void newTrack(String trackName, int albumID);
    void deleteTrack(int trackID);

    /**
     * 
     * @param albumSalesID
     * @return
     */
    List<AlbumSales> getAlbumSales(int albumSalesID);
    void updateAlbumSale(int albumSalesID, int albumID, int albumsSold);
    void newAlbumSale(int albumID, int albumsSold);
    void deleteAlbumSales(int albumSalesID);
}
