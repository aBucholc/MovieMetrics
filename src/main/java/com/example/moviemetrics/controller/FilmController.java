package com.example.moviemetrics.controller;

import com.example.moviemetrics.model.Film;
import com.example.moviemetrics.model.FilmResponse;
import com.example.moviemetrics.service.FilmService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    public FilmResponse getFilmDetails(@PathVariable("title") String filmTitle) throws UnsupportedEncodingException {
        String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=YOUR_API_KEY=" + filmTitle;

        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String jsonResponse = response.body().string();
                FilmResponse filmResponse = mapJsonToFilmResponse(jsonResponse);
                return filmResponse;
            } else {
                // Obsługa błędów
//                return "Error: " + response.code();
            }
        } catch (IOException e) {
            // Obsługa wyjątków
            e.printStackTrace();
//            return "Error: " + e.getMessage();
        }
        return null;
    }
    @PostMapping("/{title}")
    public String saveFilmDetails(@PathVariable("title") String filmTitle) throws UnsupportedEncodingException {
        FilmResponse filmResponse = getFilmDetails(filmTitle);

        if (filmResponse != null) {
            Film film = new Film();
            film.setTitle(filmResponse.getTitle());
            film.setYear(filmResponse.getYear());
            film.setDirector(filmResponse.getDirector());
            film.setImdbRating(filmResponse.getImdbRating());

            filmService.saveFilm(film);

            return "Film saved successfully.";
        } else {
            return "Failed to save film.";
        }
    }


    private FilmResponse mapJsonToFilmResponse(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        FilmResponse filmResponse = new FilmResponse();
        filmResponse.setTitle(jsonObject.get("Title").getAsString());
        filmResponse.setYear(jsonObject.get("Year").getAsInt());
        filmResponse.setDirector(jsonObject.get("Director").getAsString());
        filmResponse.setImdbRating(jsonObject.get("imdbRating").getAsDouble());

        return filmResponse;
    }
}


