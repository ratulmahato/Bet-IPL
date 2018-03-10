package com.learning.BetIPL.service;

import com.learning.BetIPL.dao.model.User;

public interface UserService {

	public User findByEmail(String email);

	public User findByConfirmationToken(String confirmationToken);

	public void createUser(User user);
	
	public void updateUser(User user);

}