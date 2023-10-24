package com.upc.ejercicio.Ejercicio.repository;

import com.upc.ejercicio.Ejercicio.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByEditorial(String editorial);
    boolean existsByTitleAndEditorial(String title, String editorial);

}
