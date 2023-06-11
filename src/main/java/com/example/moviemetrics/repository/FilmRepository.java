package com.example.moviemetrics.repository;

import com.example.moviemetrics.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
