package com.example.moviemetrics.service;

import com.example.moviemetrics.model.Film;
import com.example.moviemetrics.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FilmServiceTest {

    @MockBean
    private FilmRepository filmRepository;

    private FilmService filmService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        filmService = new FilmServiceImpl(filmRepository);
    }

    @Test
    public void testGetFilmById() {
        long filmId = 1L;
        Film expectedFilm = new Film();
        expectedFilm.setId(filmId);
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(expectedFilm));

        Optional<Film> film = filmService.getFilmById(filmId);

        assertEquals(filmId, film.get().getId());
        verify(filmRepository, times(1)).findById(filmId);
    }

    @Test
    public void testSaveFilm() {
        Film film = new Film();
        film.setTitle("Example Film");

        filmService.saveFilm(film);

        verify(filmRepository, times(1)).save(film);
    }

    // Dodaj inne testy jednostkowe dla FilmService

}
