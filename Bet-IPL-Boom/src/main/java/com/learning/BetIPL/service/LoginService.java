package com.learning.BetIPL.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public LoginService() {
		super();
	}

	public boolean validateUser(String userId, String password) {
		return userId.equalsIgnoreCase("ratul")
                && password.equalsIgnoreCase("welcomeRatul");
	}

}
