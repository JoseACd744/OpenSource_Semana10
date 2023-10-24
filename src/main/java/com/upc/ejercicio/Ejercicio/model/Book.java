package com.upc.ejercicio.Ejercicio.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="books") // <--- Add this line

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", length = 22, nullable = false)
    private String title;
    @Column(name="editorial", length = 14, nullable = false)
    private String editorial;
}
