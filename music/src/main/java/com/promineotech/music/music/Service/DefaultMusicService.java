package com.promineotech.music.music.Service;

import java.util.List;

import com.promineotech.music.music.DAO.AlbumDAO;
import com.promineotech.music.music.DAO.AlbumSalesDAO;
import com.promineotech.music.music.DAO.ArtistDAO;
import com.promineotech.music.music.DAO.GenreDAO;
import com.promineotech.music.music.DAO.TrackDAO;
import com.promineotech.music.music.Entities.AlbumSales;
import com.promineotech.music.music.Entities.Albums;
import com.promineotech.music.music.Entities.Artists;
import com.promineotech.music.music.Entities.Genres;
import com.promineotech.music.music.Entities.Tracks;

import org.springframework.stereotype.Service;

@Service
public class DefaultMusicService implements MusicService{

//=================ARTISTS=========================//

    @Override
    public List<Artists> getArtists() {
        return ArtistDAO.listAllArtists();
    }

    @Override
    public void updateArist(int artistID, String firstName, String lastName, int age) {
        ArtistDAO.updateArtist(artistID, firstName, lastName, age);
        
    }

    @Override
    public void newArtist(String firstName, String lastName, int age) {
        ArtistDAO.newArtist(firstName, lastName, age);
        
    }

    @Override
    public void deleteArtist(int artistID) {
        ArtistDAO.deleteArtist(artistID);
        
    }
//==============ALBUMS===========================//

    @Override
    public List<Albums> getAlbums(int albumID) {
        return AlbumDAO.getAlbums();
    }

    @Override
    public void updateAlbum(int albumID, String albumName, int artistID, int genreID) {
        AlbumDAO.updateAlbum(albumID, albumName, artistID, genreID);
        
    }

    @Override
    public void newAlbum(String albumName, int artistID, int genreID) {
        AlbumDAO.newAlbum(albumName, artistID, genreID);
        
    }

    @Override
    public void deleteAlbum(int albumID) {
        AlbumDAO.deleteAlbum(albumID);
        
    }

//======================GENRES===========================//

    @Override
    public List<Genres> getGenres(int genreID) {
        return GenreDAO.getGenres();
    }

    @Override
    public void updateGenre(int genreID, String genreName) {
        GenreDAO.updateGenre(genreID, genreName);
        
    }

    @Override
    public void newGenre(String genreName) {
        GenreDAO.newGenre(genreName);
        
    }

    @Override
    public void deleteGenre(int genreID) {
        GenreDAO.deleteGenre(genreID);
        
    }

//========================TRACKS==========================//
    @Override
    public List<Tracks> getTracks(int trackID) {
        return TrackDAO.getTracks();
    }

    @Override
    public void updateTrack(int trackID, String trackName, int albumID) {
        TrackDAO.updateTrack(trackID, trackName, albumID);
        
    }

    @Override
    public void newTrack(String trackName, int albumID) {
        TrackDAO.newTrack(trackName, albumID);
        
    }

    @Override
    public void deleteTrack(int trackID) {
        TrackDAO.deleteTrack(trackID);
        
    }

//=========================ALBUM SALES====================//
    @Override
    public List<AlbumSales> getAlbumSales(int albumSalesID) {
        return AlbumSalesDAO.getAlbumSales();
    }

    @Override
    public void updateAlbumSale(int albumSalesID, int albumID, int albumsSold) {
        AlbumSalesDAO.updateAlbumSale(albumSalesID, albumID, albumsSold);
        
    }

    @Override
    public void newAlbumSale(int albumID, int albumsSold) {
        AlbumSalesDAO.newAlbumSale(albumID, albumsSold);
        
    }

    @Override
    public void deleteAlbumSales(int albumSalesID) {
        AlbumSalesDAO.deleteAlbumSales(albumSalesID);
        
    }
    
}
