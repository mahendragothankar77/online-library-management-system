package com.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.entity.Book;
import com.library.entity.BookIssue;
import com.library.entity.BookStatus;
import com.library.entity.Fine;
import com.library.exception.BookNotAvailableException;
import com.library.services.BookIssueService;
import com.library.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookIssueService bookIssueService;

	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
		Book savedBook = bookService.addBook(book);
		return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBook(@RequestParam(required = false) String name, @RequestParam(required = false) String author,
			@RequestParam(required = false) String category, @RequestParam(required = false) BookStatus status) {
		 List<Book> searchBook = bookService.search(name, author, category, status);
		return new ResponseEntity<List<Book>>(searchBook,HttpStatus.OK);
	}

	@GetMapping("/allBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}

	@PostMapping("/issue")
	public ResponseEntity<BookIssue> issueBook(@RequestParam Long bookId, @RequestParam int days, Principal principal)
			throws BookNotAvailableException {
		BookIssue issue = bookIssueService.issueBook(bookId, days, principal.getName());

		return new ResponseEntity<>(issue, HttpStatus.CREATED);
	}

	@PostMapping("/return")
	public ResponseEntity<Fine> returnBook(@RequestParam Long issueId) {
        Fine fine = bookIssueService.returnBook(issueId);
        return ResponseEntity.ok(fine);
    }

}
