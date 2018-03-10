package com.learning.BetIPL.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.BetIPL.dao.model.User;
import com.learning.BetIPL.dao.repository.UserRepository;
import com.learning.BetIPL.framework.entity.SearchCriteria;
import com.learning.BetIPL.service.LoginService;
import com.learning.BetIPL.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {


	@Autowired
	private UserRepository userRepository;
	
	public boolean validateUser(String userId, String password) {
		SearchCriteria sc=new SearchCriteria("email",userId);
		User user=userRepository.searchOne(sc,true);
		if(null==user){
			return false;
		}
		boolean isValid=password.equals(user.getPassword());
		return isValid;
	}

}
