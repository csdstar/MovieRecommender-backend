package org.example.backend.controller;

import org.example.backend.dto.movie_details.MovieDTO;
import org.example.backend.service.MovieDetails_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieDetails_Service movieDetails_Service;

    @Autowired
    public MovieController(MovieDetails_Service movieDetails_Service) {
        this.movieDetails_Service = movieDetails_Service;
    }

    @GetMapping("/details")
    public List<MovieDTO> getMovieDetails(Integer userId){
        return movieDetails_Service.getMovieDetails(userId);
    }
}
