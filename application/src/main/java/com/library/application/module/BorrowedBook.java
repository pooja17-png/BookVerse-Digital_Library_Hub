package com.library.application.module;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowedBook {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrowedBookId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;
	public Long getBorrowedBookId() {
		return borrowedBookId;
	}
	public void setBorrowedBookId(Long borrowedBookId) {
		this.borrowedBookId = borrowedBookId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	public BorrowedBook(Long borrowedBookId, User user, Book book, LocalDate borrowDate, LocalDate dueDate,
			boolean returned) {
		super();
		this.borrowedBookId = borrowedBookId;
		this.user = user;
		this.book = book;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returned = returned;
	}
	public BorrowedBook() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BorrowedBook [borrowedBookId=" + borrowedBookId + ", user=" + user + ", book=" + book + ", borrowDate="
				+ borrowDate + ", dueDate=" + dueDate + ", returned=" + returned + "]";
	}

    
    
}
