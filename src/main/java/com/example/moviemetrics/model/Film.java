package com.example.moviemetrics.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column
    private int year;
}
