package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Book;
import com.library.entity.BookStatus;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByCategory(String category);

	List<Book> findByAuthorContaining(String author);

	List<Book> findByNameContaining(String name);

	List<Book> findByStatus(BookStatus status);
}
