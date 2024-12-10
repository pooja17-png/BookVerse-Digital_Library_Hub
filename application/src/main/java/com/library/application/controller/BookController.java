package com.library.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.application.module.Book;
import com.library.application.service.BookService;


@Controller
@RequestMapping("/admin/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String viewBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "managebooks";  // Thymeleaf template for managing books
    }

   @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
      return "addbook";  // Thymeleaf template for adding a new book
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/admin/books";
   }

   @GetMapping("/edit/{id}")
   public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editbook";  // Thymeleaf template for editing a book
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute("book") Book book) {
        bookService.updateBook(id, book);
        return "redirect:/admin/books";
    }

   @GetMapping("/delete/{id}")
   public String deleteBook(@PathVariable Long id) {
       bookService.deleteBook(id);
       return "redirect:/admin/books";
   }
}
