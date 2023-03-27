package com.example.lab8movielist.controller;

import com.example.lab8movielist.entity.Movie;
import com.example.lab8movielist.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@Slf4j
@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/list")
    public ModelAndView getAllMovie() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView ("list-movie");
        mav.addObject("movie", movieRepository.findAll());
        return mav;
    }

    @GetMapping("/addMovieForm")
    public ModelAndView addMovieForm() {
        ModelAndView mav = new ModelAndView("add-movie-form");
        Movie movie = new Movie();
        mav.addObject("movie", movie);
        return mav;
    }

    @PostMapping("/saveMovie")
    public  String saveMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long movieId) {
        ModelAndView mav = new ModelAndView("add-movie-form");
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = new Movie();
        if (optionalMovie.isPresent()){
            movie = optionalMovie.get();
        }
        mav.addObject("movie", movie);
        return mav;
    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(@RequestParam Long movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/list";
    }

}
