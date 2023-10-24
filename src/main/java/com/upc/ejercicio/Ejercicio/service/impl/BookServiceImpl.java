package com.upc.ejercicio.Ejercicio.service.impl;

import com.upc.ejercicio.Ejercicio.model.Book;
import com.upc.ejercicio.Ejercicio.repository.BookRepository;
import com.upc.ejercicio.Ejercicio.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

}
