package controller;

import java.util.List;

import entity.City;
import entity.Movie;
import entity.Theater;
import service.SearchService;

public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    public List<String> searchMoviesByCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null.");
        }

        List<Movie> movies = searchService.searchMoviesByCity(city);
        if (movies.isEmpty()) {
            System.out.println("No movies currently showing in " + city.getName());
            return List.of();
        }

        System.out.println("Movies in " + city.getName() + ":");
        movies.forEach(movie -> System.out.println("- " + movie.getTitle()));
        return movies.stream().map(Movie::getTitle).toList();
    }

    public List<String> searchMoviesInTheater(Theater theater) {
        if (theater == null) {
            throw new IllegalArgumentException("Theater cannot be null.");
        }

        List<Movie> movies = searchService.searchMoviesInTheater(theater);
        if (movies.isEmpty()) {
            System.out.println("No movies currently showing in theater " + theater.getName());
            return List.of();
        }

        System.out.println("Movies in theater " + theater.getName() + ":");
        movies.forEach(movie -> System.out.println("- " + movie.getTitle()));
        return movies.stream().map(Movie::getTitle).toList();
    }
}
