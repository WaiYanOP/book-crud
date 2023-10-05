package com.example.bookcrud.dao;

import com.example.bookcrud.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
