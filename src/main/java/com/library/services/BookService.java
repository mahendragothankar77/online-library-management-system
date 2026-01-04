package com.library.services;

import java.util.List;

import com.library.entity.Book;
import com.library.entity.BookStatus;

public interface BookService {
	public Book addBook(Book book);

	public Book getBook(Long id);

	public List<Book> getAllBooks();

	public List<Book> search(String name, String author, String category, BookStatus status);


	

}
