package com.example.moviemetrics.service;

import com.example.moviemetrics.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    List<Film> getAllFilms();
    Optional<Film> getFilmById(Long id);
    Optional<Film> createFilm(Film film);
    Optional<Film> updateFilm(Long id, Film film);
    void deleteFilm(Long id);
    void saveFilm(Film film);
}
