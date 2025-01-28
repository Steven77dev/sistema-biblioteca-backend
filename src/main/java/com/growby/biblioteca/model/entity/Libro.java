package com.growby.biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Long idAutor;
    private String isbn;
    private Date fechaPublicacion;
    private String estado;


}