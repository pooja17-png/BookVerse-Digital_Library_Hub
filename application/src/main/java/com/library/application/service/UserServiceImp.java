package com.library.application.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.module.User;
import com.library.application.repository.BorrowedBookRepository;
import com.library.application.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<BorrowedBook> getUserBorrowedBooks(Long userId) {
        return borrowedBookRepository.findByUserUserId(userId);
    }

    @Override
    public void borrowBook(User user, Book book) {
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setUser(user);
        borrowedBook.setBook(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setDueDate(LocalDate.now().plusWeeks(2)); // Borrowing for 2 weeks
        borrowedBook.setReturned(false);

        book.setCopies(book.getCopies() - 1); // Decrease copies
        borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public void returnBook(BorrowedBook borrowedBook) {
        borrowedBook.setReturned(true);
        Book book = borrowedBook.getBook();
        book.setCopies(book.getCopies() + 1); // Increase copies
        borrowedBookRepository.save(borrowedBook);
    }

	@Override
	public List<User> userList() {
		List<User> findAll = userRepository.findAll();
		return findAll;
	}
	
}
