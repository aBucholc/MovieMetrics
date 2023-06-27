package com.example.moviemetrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmResponse {
    private String title;
    private int year;
    private String director;
    private Double imdbRating;
}
