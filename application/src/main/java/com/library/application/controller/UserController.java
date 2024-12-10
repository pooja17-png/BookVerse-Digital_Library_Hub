package com.library.application.controller;

import java.util.List;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.application.module.Book;
import com.library.application.module.BorrowedBook;
import com.library.application.module.User;
import com.library.application.module.UserLogin;
import com.library.application.service.BookService;
import com.library.application.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.register(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult br, HttpSession session, Model model) {
        if (br.hasErrors()) {
            return "login";
        }
        List<User> dbUser = userService.userList();
        boolean found = false;
        Long userId = null;

        for (User ur : dbUser) {
            if (ur.getUsername().equals(userLogin.getUsername()) && ur.getPassword().equals(userLogin.getPassword())) {
                found = true;
                userId = ur.getUserId(); // Get the userId
                break;
            }
        }

        if (found) {
            session.setAttribute("userId", userId); // Store userId in session
            return "redirect:/user/dashboard";
        } else {
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login"; // Redirect to login if session has no userId
        }
        User user = userService.getUserById(userId);
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("user", user);
        model.addAttribute("books", books);
        return "dashboard";
    }

    @PostMapping("/borrow")
    public String borrowBook(HttpSession session, @RequestParam Long bookId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login"; // Redirect to login if session has no userId
        }
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);
        userService.borrowBook(user, book);
        return "redirect:/user/dashboard";
    }

//    @GetMapping("/borrowed")
//    public String showBorrowedBooks(HttpSession session, Model model) {
//        Long userId = (Long) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/user/login"; // Redirect to login if session has no userId
//        }
//        List<BorrowedBook> borrowedBooks = userService.getUserBorrowedBooks(userId);
//        model.addAttribute("borrowedBooks", borrowedBooks);
//        return "borrowedbooks";
//    }
    
    @GetMapping("/borrowed")
    public String showBorrowedBooks(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/user/login"; // Redirect to login if session has no userId
        }
        List<BorrowedBook> borrowedBooks = userService.getUserBorrowedBooks(userId);
        model.addAttribute("borrowedBooks", borrowedBooks);
        return "borrowedbooks";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> searchResults = bookService.searchBooks(query);
        model.addAttribute("books", searchResults);
        return "dashboard"; // Returning to the same dashboard page with search results
    }
}
