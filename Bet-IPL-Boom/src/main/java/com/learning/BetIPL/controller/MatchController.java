package com.learning.BetIPL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learning.BetIPL.bean.Match;
import com.learning.BetIPL.service.LoginService;
import com.learning.BetIPL.service.MatchServiceImpl;

@Controller
@SessionAttributes("name")
public class MatchController {

	
		@Autowired
		private MatchServiceImpl matchServiceImpl;
		
		@RequestMapping(value="/schedule", method = RequestMethod.GET)
	    public List<Match> showSchedulePage(ModelMap model){
			return matchServiceImpl.getAllMatches();
	    }
	   

	}

	

