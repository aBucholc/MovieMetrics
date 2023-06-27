package com.example.moviemetrics.controller;
import com.example.moviemetrics.model.Film;
import com.example.moviemetrics.model.FilmResponse;
import com.example.moviemetrics.service.FilmService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FilmControllerTests {

    @Mock
    private FilmService filmService;

    @Test
    public void testGetFilmDetails() throws UnsupportedEncodingException {

        String filmTitle = "Example Film";
        FilmResponse mockFilmResponse = new FilmResponse();
        mockFilmResponse.setTitle("Example Film");
        mockFilmResponse.setYear(2021);
        mockFilmResponse.setDirector("John Doe");
        mockFilmResponse.setImdbRating(7.5);


        FilmController filmController = new FilmController(filmService);
        FilmController spyFilmController = spy(filmController);
        doReturn(mockFilmResponse).when(spyFilmController).getFilmDetails(filmTitle);


        FilmResponse filmResponse = spyFilmController.getFilmDetails(filmTitle);


        assertNotNull(filmResponse);
        assertEquals("Example Film", filmResponse.getTitle());
        assertEquals(2021, filmResponse.getYear());
        assertEquals("John Doe", filmResponse.getDirector());
        assertEquals(7.5, filmResponse.getImdbRating(), 0.01);
    }

    @Test
    public void testSaveFilmDetails() throws UnsupportedEncodingException {

        String filmTitle = "Example Film";
        FilmResponse mockFilmResponse = new FilmResponse();
        mockFilmResponse.setTitle("Example Film");
        mockFilmResponse.setYear(2021);
        mockFilmResponse.setDirector("John Doe");
        mockFilmResponse.setImdbRating(7.5);

        FilmController filmController = new FilmController(filmService);
        FilmController spyFilmController = spy(filmController);
        doReturn(mockFilmResponse).when(spyFilmController).getFilmDetails(filmTitle);

        String result = spyFilmController.saveFilmDetails(filmTitle);

        assertEquals("Film saved successfully.", result);
        verify(filmService, times(1)).saveFilm(any(Film.class));
    }
}