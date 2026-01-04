package com.library.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.entity.BookIssue;
import com.library.entity.User;
import com.library.repository.BookIssueRepository;
import com.library.services.BookIssueService;
import com.library.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private BookIssueService issueService;

	@Autowired
	private UserService userService;
	@Autowired
	private BookIssueRepository bookIssueRepository;

	 @GetMapping("/allUser")
	    public ResponseEntity<List<User>> getAllUsers() {
	        return ResponseEntity.ok(userService.getAllUsers());
	    }

	@GetMapping("/me")
	public ResponseEntity<List<BookIssue>> getMyBooks(Principal principal) {

        User user = userService.getByEmail(principal.getName());
        List<BookIssue> issues = bookIssueRepository.findByUser(user);

        return ResponseEntity.ok(issues);
    }

	@GetMapping("/{id}/history")
	public List<BookIssue> history(@PathVariable Long id) {
		return issueService.history(id);
	}
}
