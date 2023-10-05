package com.example.bookcrud.service;

import com.example.bookcrud.dao.AuthorDao;
import com.example.bookcrud.dao.BookDao;
import com.example.bookcrud.entity.Author;
import com.example.bookcrud.entity.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    public BookService(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }
    @Transactional
    public void saveBook(Book book){
        Author author = book.getAuthor();
        author.addBook(book);
        bookDao.save(book);
    }

    public List<Book> findAllBooks(){
        return bookDao.findAll();
    }
    public void removeAuthor(int id){
        authorDao.deleteById(id);
    }
    public void saveAuthor(Author author){
        authorDao.save(author);
    }

    public List<Author> listAuthors(){
        return authorDao.findAll();
    }

    public Author findAuthorById(int id){
        return authorDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void updateAuthorV2(Author author) {
        authorDao.saveAndFlush(author);
    }

    @Transactional
    public void updateAuthorV1(int id, Author author){
        Author existingAuthor = authorDao.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        existingAuthor.setBooks(author.getBooks());
        existingAuthor.setId(author.getId());
        existingAuthor.setName(author.getName());
        existingAuthor.setAddress(author.getAddress());
        existingAuthor.setPhoneNumber(author.getPhoneNumber());
        existingAuthor.setDateOfBirth(author.getDateOfBirth());
    }

    public void removeBook(int id){
        bookDao.deleteById(id);
    }

    public Book findBook(int id){
        return bookDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateBookV1(int id, Book book){
        Book existingBook = bookDao.findById(id).orElseThrow(EntityNotFoundException::new);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setPrice(book.getPrice());
        existingBook.setImageUrl(book.getImageUrl());
        existingBook.setPublisher(book.getPublisher());
    }
}
