package com.library.application.module;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowedBooks = new ArrayList<>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BorrowedBook> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}

	public User(Long userId, String username, String password, String email, List<BorrowedBook> borrowedBooks) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.borrowedBooks = borrowedBooks;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", borrowedBooks=" + borrowedBooks + "]";
	}

    
    
}
