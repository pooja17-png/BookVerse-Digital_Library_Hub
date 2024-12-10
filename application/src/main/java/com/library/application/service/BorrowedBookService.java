package com.library.application.service;

import java.util.List;

import com.library.application.module.BorrowedBook;

public interface BorrowedBookService {

	public List<BorrowedBook> getBorrowedBooksByUserId(Long userId);
	public void updateReturnStatus(Long borrowedBookId, String status);
}
