package com.library.application.controller;

import com.library.application.module.Admin;
import com.library.application.module.AdminLogin;
import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.module.User;
import com.library.application.service.AdminService;
import com.library.application.service.BookService;
import com.library.application.service.BorrowedBookService;
import com.library.application.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowedBookService borrowedBookService;

    @Autowired
    private BookService bookService;

    // --- User Management ---

    // Display all users
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "user_list"; // Thymeleaf template for displaying users
    }

    // View borrowed books by user
    @GetMapping("/users/{userId}/borrowed-books")
    public String getBorrowedBooksByUser(@PathVariable("userId") Long userId, Model model) {
        List<BorrowedBook> borrowedBooks = borrowedBookService.getBorrowedBooksByUserId(userId);
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "borrowed_books_list"; // Thymeleaf template for displaying borrowed books
    }

    // Update the return status of a borrowed book
    @PostMapping("/borrowed-books/{borrowedBookId}/update-status")
    public String updateReturnStatus(@PathVariable("borrowedBookId") Long borrowedBookId,
                                     @RequestParam("status") String status) {
        borrowedBookService.updateReturnStatus(borrowedBookId, status);
        return "redirect:/admin/users"; // Redirect to the user list after updating the status
    }

    // --- Book Management ---

    // Display all books
    @GetMapping("/books")
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "managebooks";  // Thymeleaf template for managing books
    }

    // Add book form
    @GetMapping("/books/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";  // Thymeleaf template for adding a new book
    }

    // Add a new book
    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/admin/books";
    }

    // Edit book form
    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editbook";  // Thymeleaf template for editing a book
    }

    // Edit an existing book
    @PostMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/admin/books";
    }

    // Delete a book
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/admin/books";
    }
    
    
   
}
