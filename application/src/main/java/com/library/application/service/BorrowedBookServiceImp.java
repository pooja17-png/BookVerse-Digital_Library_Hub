package com.library.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.repository.BorrowedBookRepository;

@Service
public class BorrowedBookServiceImp implements BorrowedBookService {

	@Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public List<BorrowedBook> getBorrowedBooksByUserId(Long userId) {
        return borrowedBookRepository.findByUserUserId(userId);
    }

    public void updateReturnStatus(Long borrowedBookId, String status) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId).orElse(null);
        if (borrowedBook != null) {
            borrowedBook.setReturned(true);
            Book book = borrowedBook.getBook();
            book.setCopies(book.getCopies() + 1);
            borrowedBookRepository.save(borrowedBook);
        }
     }
}