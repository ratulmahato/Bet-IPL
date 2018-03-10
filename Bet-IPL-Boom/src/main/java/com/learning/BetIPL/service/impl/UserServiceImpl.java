package com.learning.BetIPL.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.BetIPL.dao.model.User;
import com.learning.BetIPL.dao.repository.UserRepository;
import com.learning.BetIPL.framework.entity.SearchCriteria;
import com.learning.BetIPL.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		SearchCriteria sc = new SearchCriteria("email", email);
		return userRepository.searchOne(sc,true);
	}

	public User findByConfirmationToken(String confirmationToken) {

		SearchCriteria sc = new SearchCriteria("confirmationToken", confirmationToken);
		return userRepository.searchOne(sc,false);
	}

	public void createUser(User user) {
		userRepository.create(user);
	}
	public void updateUser(User user) {
		userRepository.update(user);
	}

}