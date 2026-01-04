package com.library.serviceImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.Book;
import com.library.entity.BookIssue;
import com.library.entity.BookStatus;
import com.library.entity.Fine;
import com.library.entity.User;
import com.library.exception.BookNotAvailableException;
import com.library.exception.MembershipExpiredException;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.BookIssueRepository;
import com.library.repository.BookRepository;
import com.library.repository.FineRepository;
import com.library.repository.UserRepository;
import com.library.services.BookIssueService;

@Service
public class BookIssueServiceImpl implements BookIssueService {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BookIssueRepository issueRepo;
	@Autowired
	private FineRepository fineRepo;

	@Override
	public List<BookIssue> history(Long userId) {
		return issueRepo.findByUserId(userId);
	}

	@Override
	public BookIssue issueBook(Long bookId, int days, String email) throws BookNotAvailableException {

		User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		if (user.getMembershipEndDate().isBefore(LocalDate.now()))
			throw new MembershipExpiredException("Membership expired");

		Book book = bookRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

		if (book.getAvailableQuantity() <= 0)
			throw new BookNotAvailableException("Book not available");

		book.setAvailableQuantity(book.getAvailableQuantity() - 1);
		if (book.getAvailableQuantity() == 0)
			book.setStatus(BookStatus.NOT_AVAILABLE);

		BookIssue issue = new BookIssue();
		issue.setBook(book);
		issue.setUser(user);
		issue.setIssueDate(LocalDate.now());
		issue.setDueDate(LocalDate.now().plusDays(days));

		bookRepo.save(book);
		return issueRepo.save(issue);
	}

	@Override
	public Fine returnBook(Long issueId) {

		BookIssue issue = issueRepo.findById(issueId)
				.orElseThrow(() -> new ResourceNotFoundException("Issue not found"));

		issue.setReturnDate(LocalDate.now());

		Book book = issue.getBook();
		book.setAvailableQuantity(book.getAvailableQuantity() + 1);
		book.setStatus(BookStatus.AVAILABLE);
		bookRepo.save(book);

		int fineAmount = 0;
		if (LocalDate.now().isAfter(issue.getDueDate()))
			fineAmount = (int) ChronoUnit.DAYS.between(issue.getDueDate(), LocalDate.now()) * 10;

		Fine fine = new Fine();
		fine.setIssue(issue);
		fine.setAmount(fineAmount);
		return fineRepo.save(fine);
	}

}
