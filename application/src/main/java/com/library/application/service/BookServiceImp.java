package com.library.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.repository.BookRepository;
import com.library.application.repository.BorrowedBookRepository;

@Service
public class BookServiceImp implements BookService {
	
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
	private BorrowedBookRepository borrowedBookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Long bookId, Book book) {
		// TODO Auto-generated method stub
		book.setBookId(bookId);
		return bookRepository.save(book);
	}

	public void deleteBook(Long bookId) {
	    Optional<BorrowedBook> borrowedBooks = borrowedBookRepository.findById(bookId);
	    
	    if (!borrowedBooks.isEmpty()) {
	        throw new IllegalStateException("Cannot delete the book. It has been borrowed by users.");
	    }

	    bookRepository.deleteById(bookId);
	}
	
	 public List<Book> searchBooks(String query) {
	        return bookRepository.findByTitleContainingOrAuthorContainingOrCategoryContaining(query, query, query);
	    }
	
}
