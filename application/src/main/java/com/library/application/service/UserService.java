package com.library.application.service;

import java.util.List;

import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.module.User;

public interface UserService {

	public void register(User user);
    public User login(String username, String password);
    public User getUserById(Long userId);
    public List<BorrowedBook> getUserBorrowedBooks(Long userId);
    public void borrowBook(User user, Book book);
    public void returnBook(BorrowedBook borrowedBook);
    public List<User>userList();
}
