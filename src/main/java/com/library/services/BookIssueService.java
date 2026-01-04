package com.library.services;

import java.util.List;

import com.library.entity.BookIssue;
import com.library.entity.Fine;
import com.library.exception.BookNotAvailableException;

public interface BookIssueService {

	public List<BookIssue> history(Long userId);

	public BookIssue issueBook(Long bookId, int days, String email) throws BookNotAvailableException;

	public Fine returnBook(Long issueId);

}
