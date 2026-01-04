package com.library.services;

import java.util.List;

import com.library.entity.User;

public interface UserService {

	public User register(User user, Integer months);

	public User getUser(Long id);

	public List<User> getAllUsers();

	public boolean isMembershipValid(Long userId);

	public User getByEmail(String email);
}
