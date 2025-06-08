package org.example.backend.controller;

import lombok.extern.log4j.Log4j2;
import org.example.backend.dto.movie_details.MovieDTO;
import org.example.backend.service.MovieDetails_Service;
import org.example.backend.utils.CurrentUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieDetails_Service movieDetails_Service;

    @Autowired
    public MovieController(MovieDetails_Service movieDetails_Service) {
        this.movieDetails_Service = movieDetails_Service;
    }
    @GetMapping("/search")
    public List<MovieDTO> searchMovies(@RequestParam("search") String keyword) {
        log.info("searchMovies, keyword = {}", keyword);
        return movieDetails_Service.searchMovies(keyword);
    }

    @GetMapping("/details")
    public List<MovieDTO> getMovieDetails(@CurrentUserId Integer userId) {
        log.info("getMovieDetails,id = {}", userId);
        return movieDetails_Service.getMovieDetails(userId);
    }
}
