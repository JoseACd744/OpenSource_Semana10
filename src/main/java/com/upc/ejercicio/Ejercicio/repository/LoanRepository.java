package com.upc.ejercicio.Ejercicio.repository;

import com.upc.ejercicio.Ejercicio.model.Book;
import com.upc.ejercicio.Ejercicio.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long>{

    boolean existsByCodeStudent(String codeStudent);
    boolean existsByCodeStudentAndBookAndBookLoan(String codeStudent, Book book, boolean bookLoan);
    List<Loan> findByCodeStudent(String codeStudent);
}
