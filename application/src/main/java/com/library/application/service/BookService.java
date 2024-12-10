package com.library.application.service;

import java.util.List;

import com.library.application.module.Book;

public interface BookService {

	public List<Book> getAllBooks();
    public Book getBookById(Long bookId);
    public Book addBook(Book book);
    public Book updateBook(Long bookId, Book book);
    public void deleteBook(Long bookId);
    public List<Book> searchBooks(String query);
}
