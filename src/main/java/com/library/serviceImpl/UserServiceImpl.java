package com.library.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.entity.Role;
import com.library.entity.User;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.UserRepository;
import com.library.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user, Integer months) {

		user.setPassword(encoder.encode(user.getPassword()));

		if ("admin@library.com".equalsIgnoreCase(user.getEmail())) {
			user.setRole(Role.ADMIN);
		} else {
			user.setRole(Role.USER);
		}
		user.setMembershipStartDate(LocalDate.now());
		user.setMembershipEndDate(LocalDate.now().plusMonths(months));
		user.setActive(true);

		return repo.save(user);
	}

	@Override
	public User getUser(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public boolean isMembershipValid(Long userId) {
		return getUser(userId).getMembershipEndDate().isAfter(LocalDate.now());
	}

	@Override
	public User getByEmail(String email) {
		return repo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
}
