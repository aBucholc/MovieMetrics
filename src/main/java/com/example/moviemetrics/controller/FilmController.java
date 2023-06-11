package com.example.moviemetrics.controller;

import com.example.moviemetrics.model.Film;
import com.example.moviemetrics.model.FilmDetails;
import com.example.moviemetrics.model.FilmResponse;
import com.example.moviemetrics.service.FilmService;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    @GetMapping("/{title}")
    public String getFilmDetails(@PathVariable("title") String filmTitle) throws UnsupportedEncodingException {
        String apiKey = "463c6aa3";
        String encodedFilmTitle = URLEncoder.encode(filmTitle, "UTF-8");
        String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + encodedFilmTitle;

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                FilmResponse filmResponse = gson.fromJson(response.body().string(), FilmResponse.class);
                return gson.toJson(filmResponse);
            } else {
                // Obsługa błędów
                return "Error: " + response.code();
            }
        } catch (IOException e) {
            // Obsługa wyjątków
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    @PostMapping("/{title}")
    public String saveFilmDetails(@PathVariable("title") String filmTitle) throws UnsupportedEncodingException {
        String filmDetailsJson = getFilmDetails(filmTitle);
        FilmDetails filmDetails = gson.fromJson(filmDetailsJson, FilmDetails.class);

        if (filmDetails != null) {
            Film film = new Film();
            film.setTitle(filmDetails.getTitle());
            film.setYear(Integer.parseInt(filmDetails.getYear()));
            film.setDirector(filmDetails.getDirector());

            filmService.saveFilm(film);

            return "Film saved successfully.";
        } else {
            return "Failed to save film.";
        }
    }
}


