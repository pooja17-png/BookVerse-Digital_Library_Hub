package com.library.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.application.module.BorrowedBook;
import com.library.application.module.User;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Long> {

	 List<BorrowedBook> findByUserUserId(Long userId);
}
