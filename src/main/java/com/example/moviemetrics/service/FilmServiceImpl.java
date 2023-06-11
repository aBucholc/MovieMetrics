package com.example.moviemetrics.service;

import com.example.moviemetrics.model.Film;
import com.example.moviemetrics.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;


    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public Optional<Film> createFilm(Film film) {
        Film savedFilm = filmRepository.save(film);
        return Optional.ofNullable(savedFilm);
    }

    @Override
    public Optional<Film> updateFilm(Long id, Film film) {
        return filmRepository.findById(id)
                .map(existingFilm -> {
                    existingFilm.setTitle(film.getTitle());
                    existingFilm.setDirector(film.getDirector());
                    existingFilm.setYear(film.getYear());
                    return filmRepository.save(existingFilm);
                });
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.findById(id).ifPresent(film -> filmRepository.delete(film));
    }

    @Override
    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

}
