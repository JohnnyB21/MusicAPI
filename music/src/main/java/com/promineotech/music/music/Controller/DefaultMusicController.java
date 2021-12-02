package com.promineotech.music.music.Controller;

import java.util.List;

import com.promineotech.music.music.Entities.AlbumSales;
import com.promineotech.music.music.Entities.Albums;
import com.promineotech.music.music.Entities.Artists;
import com.promineotech.music.music.Entities.Genres;
import com.promineotech.music.music.Entities.Tracks;
import com.promineotech.music.music.Service.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultMusicController implements MusicController{

    @Autowired
    private MusicService musicService;

//-------------------Artists--------------------------
    @Override
    public ResponseEntity<List<Artists>> listArtists() {
        log.info("Artist list requested");
        List<Artists> artists = musicService.getArtists();
        return new ResponseEntity<List<Artists>>(artists, HttpStatus.OK);
    }

    @Override
    public void newArtist(String firstName, String lastName, int age) {
        musicService.newArtist(firstName, lastName, age);
        
    }

    @Override
    public void updateArist(int artistID, String firstName, String lastName, int age) {
        musicService.updateArist(artistID, firstName, lastName, age);
        
    }

    @Override
    public void deleteArtist(int artistID) {
        musicService.deleteArtist(artistID);
        
    }
//----------------Albums-------------------------

    @Override
    public ResponseEntity<List<Albums>> getAlbums(int albumID) {
        List<Albums> albums = musicService.getAlbums(albumID);
        return new ResponseEntity<List<Albums>>(albums, HttpStatus.OK);
    }

    @Override
    public void newAlbum(String albumName, int artistID, int genreID) {
        musicService.newAlbum(albumName, artistID, genreID);
        
    }

    @Override
    public void updateAlbum(int albumID, String albumName, int artistID, int genreID) {
        musicService.updateAlbum(albumID, albumName, artistID, genreID);        
    }

    @Override
    public void deleteAlbum(int albumID) {
        musicService.deleteAlbum(albumID);
        
    }
//-------------------Genres-------------------------------

    @Override
    public ResponseEntity<List<Genres>> getGenres(int genreID) {
        List<Genres> genres = musicService.getGenres(genreID);
        return new ResponseEntity<List<Genres>>(genres, HttpStatus.OK);
    }

    @Override
    public void newGenre(String genreName) {
        musicService.newGenre(genreName);      
    }

    @Override
    public void updateGenre(int genreID, String genreName) {
        musicService.updateGenre(genreID, genreName);        
    }

    @Override
    public void deleteGenre(int genreID) {
        musicService.deleteGenre(genreID);      
    }

//------------------Tracks---------------------------

    @Override
    public ResponseEntity<List<Tracks>> getTracks(int trackID) {
        List<Tracks> tracks = musicService.getTracks(trackID);
        return new ResponseEntity<List<Tracks>>(tracks, HttpStatus.OK);
    }

    @Override
    public void newTrack(String trackName, int albumID) {
        musicService.newTrack(trackName, albumID);
        
    }

    @Override
    public void updateTrack(int trackID, String trackName, int albumID) {
        musicService.updateTrack(trackID, trackName, albumID);
        
    }

    @Override
    public void deleteTrack(int trackID) {
        musicService.deleteTrack(trackID);
        
    }

//----------------------AlbumSales----------------------------

    @Override
    public ResponseEntity<List<AlbumSales>> getAlbumSales(int albumSalesID) {
        List<AlbumSales> albumSales = musicService.getAlbumSales(albumSalesID);
        return new ResponseEntity<List<AlbumSales>>(albumSales, HttpStatus.OK);
    }

    @Override
    public void newAlbumSale(int albumID, int albumsSold) {
        musicService.newAlbumSale(albumID, albumsSold);
        
    }

    @Override
    public void updateAlbumSale(int albumSalesID, int albumID, int albumsSold) {
        musicService.updateAlbumSale(albumSalesID, albumID, albumsSold);
        
    }

    @Override
    public void deleteAlbumSales(int albumSalesID) {
        musicService.deleteAlbumSales(albumSalesID);
        
    }
    
}
