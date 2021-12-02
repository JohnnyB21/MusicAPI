package com.promineotech.music.music.Controller;

import java.util.List;

import com.promineotech.music.music.Entities.AlbumSales;
import com.promineotech.music.music.Entities.Albums;
import com.promineotech.music.music.Entities.Artists;
import com.promineotech.music.music.Entities.Genres;
import com.promineotech.music.music.Entities.Tracks;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/Music")
@OpenAPIDefinition(info = @Info(title = "Music Service"), servers ={
    @Server(url = "http://localhost:8080", description = "Local server.")})
    

    public interface MusicController {
    
    // @formatter:off
    @Operation(
    summary = "Returns all artists",
    description = "Returns all artists",
    responses = {
        @ApiResponse(
            responseCode = "200", 
            description = "A list of artists is returned.", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = Artists.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid.", 
            content = @Content(
                mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Artists were found with the input criteria.", 
            content = @Content(
                mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.", 
            content = @Content(
                mediaType = "application/json"))
    }
    // ,
    // parameters = {
    //     @Parameter(
    //         name = "model", 
    //         allowEmptyValue = false, 
    //         required = false, 
    //         description = "The model name (i.e., 'WRANGLER')"),
    //     @Parameter(
    //         name = "trim", 
    //         allowEmptyValue = false, 
    //         required = false, 
    //         description = "The trim level (i.e., 'SPORT')")
    // }
    )
    
    

    
 //------------------Artists---------------------------   
    /**
     * Artist Handler
     * @return
     */
    @GetMapping("Artists")
    ResponseEntity<List<Artists>> listArtists();

    @PostMapping("Artists")
    @ResponseStatus(code = HttpStatus.OK)
    void newArtist(
        @RequestParam(required = true) String firstName,
        @RequestParam(required = true) String lastName, 
        @RequestParam(required = true) int age);
    
    @PutMapping("Artists")
    @ResponseStatus(code = HttpStatus.OK)
    void updateArist(
        @RequestParam(required = true) int artistID,
        @RequestParam(required = true) String firstName,
        @RequestParam(required = true) String lastName,
        @RequestParam(required = true) int age);

    @DeleteMapping("Artists")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteArtist(
        @RequestParam(required = true) int artistID);

//------------------Albums---------------------------
    /**
     * Album Handler
     * @param albumID
     * @return
     */
    @GetMapping("Albums")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Albums>> getAlbums(
        @RequestParam(required = true) int albumID);

    @PostMapping("Albums")
    @ResponseStatus(code = HttpStatus.OK)
    void newAlbum(
        @RequestParam(required = true) String albumName,
        @RequestParam(required = true) int artistID,
        @RequestParam(required = true) int genreID);

    @PutMapping("Albums")
    @ResponseStatus(code = HttpStatus.OK)
    void updateAlbum(
        @RequestParam(required = true) int albumID,
        @RequestParam(required = true) String albumName,
        @RequestParam(required = true) int artistID,
        @RequestParam(required = true) int genreID);

    @DeleteMapping("Albums")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteAlbum(
        @RequestParam(required = true) int albumID);

//--------------------Genres------------------------------
    /**
     * Genre Handler
     * @param genreID
     * @return
     */
    @GetMapping("Genres")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Genres>> getGenres(
        @RequestParam(required = true) int genreID);

    @PostMapping("Genres")
    @ResponseStatus(code = HttpStatus.OK)
    void newGenre(
        @RequestParam(required = true) String genreName);

    @PutMapping("Genres")
    @ResponseStatus(code = HttpStatus.OK)
    void updateGenre(
        @RequestParam(required = true) int genreID,
        @RequestParam(required = true) String genreName);

    @DeleteMapping("Genres")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteGenre(
        @RequestParam(required = true) int genreID);

//----------------Tracks--------------------------

    /**
     * Track Handler
     * @param trackID
     * @return
     */
    @GetMapping("Tracks")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<Tracks>> getTracks(
        @RequestParam(required = true) int trackID);

    @PostMapping("Tracks")
    @ResponseStatus(code = HttpStatus.OK)
    void newTrack(
        @RequestParam(required = true) String trackName,
        @RequestParam(required = true) int albumID);

    @PutMapping("Tracks")
    @ResponseStatus(code = HttpStatus.OK)
    void updateTrack(
        @RequestParam(required = true) int trackID,
        @RequestParam(required = true) String trackName,
        @RequestParam(required = true) int albumID);

    @DeleteMapping("Tracks")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteTrack(
        @RequestParam(required = true) int trackID);

//--------------------AlbumSales----------------------

    @GetMapping("AlbumSales")
    @ResponseStatus(code = HttpStatus.OK)
    ResponseEntity<List<AlbumSales>> getAlbumSales(
        @RequestParam(required = true) int albumSalesID);

    @PostMapping("AlbumSales")
    @ResponseStatus(code = HttpStatus.OK)
    void newAlbumSale(
        @RequestParam(required = true) int albumID,
        @RequestParam(required = true) int albumsSold);

    @PutMapping("AlbumSales")
    @ResponseStatus(code = HttpStatus.OK)
    void updateAlbumSale(
        @RequestParam(required = true) int albumSalesID,
        @RequestParam(required = true) int albumID,
        @RequestParam(required = true) int albumsSold);

    @DeleteMapping("AlbumSales")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteAlbumSales(
        @RequestParam(required = true) int albumSalesID);

}
