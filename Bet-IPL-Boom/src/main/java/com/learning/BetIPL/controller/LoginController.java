package com.learning.BetIPL.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.learning.BetIPL.service.LoginService;

@Controller
@SessionAttributes(value = {"userId", "username"})

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	

	    @RequestMapping("/")
	    public String home(Map<String, Object> model) {

	        model.put("message", "Index Reader !!");
	        return "index";
	    }

	@RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
		
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        boolean isValidUser = loginService.validateUser(name, password);
        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }

}
