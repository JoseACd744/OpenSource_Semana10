package com.upc.ejercicio.Ejercicio.controller;

import com.upc.ejercicio.Ejercicio.exception.ValidationException;
import com.upc.ejercicio.Ejercicio.model.Book;
import com.upc.ejercicio.Ejercicio.repository.BookRepository;
import com.upc.ejercicio.Ejercicio.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/v1")
public class BookController {
    @Autowired

    private BookService bookService;

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

//URL: localhost:8080/api/library/v1/books

//Method: GET

    @Transactional(readOnly = true)

    @GetMapping("/books")

    public ResponseEntity<List<Book>> getAllBooks() {

        return new ResponseEntity<List<Book>>(bookRepository.findAll(), HttpStatus.OK);

    }

//URL: localhost:8080/api/library/v1/books

//Method: POST

    @Transactional

    @PostMapping("/books")

    public ResponseEntity<Book> createBook(@RequestBody Book book) {

        validateBook(book);

        existBookByTitleAndEditorial(book);

        return new ResponseEntity<Book>(bookService.createBook(book), HttpStatus.CREATED);

    }

//URL: localhost:8080/api/library/v1/books/filterByEditorial

//Method: GET

    @Transactional(readOnly = true)

    @GetMapping("/books/filterByEditorial")

    public ResponseEntity<List<Book>> getAllBooksByEditorial(@RequestParam(name= "editorial") String editorial) {

        return new ResponseEntity<List<Book>>(bookRepository.findByEditorial(editorial), HttpStatus.OK);

    }

    private void validateBook(Book book){
        if(book.getTitle() == null || book.getTitle().isEmpty()){

            throw new ValidationException("El título del libro debe ser obligatorio");

        }

        if (book.getTitle().length() > 22){

            throw new ValidationException("El título del libro no debe exceder los 22 caracteres");

        }

        if(book.getEditorial() ==null || book.getEditorial().isEmpty()){
            throw new ValidationException("La editorial del libro debe ser obligatorio");
        }

        if (book.getEditorial().length() > 14){
            throw new ValidationException("La editorial del libro no debe exceder los 14 caracteres");
        }
    }

    private void existBookByTitleAndEditorial(Book book){
        if(bookRepository.existsByTitleAndEditorial(book.getTitle(), book.getEditorial())){
            throw new ValidationException("No se puede registrar el libro porque ya existe uno " +
                    "con el mismo título y editorial");

        }
    }

}
