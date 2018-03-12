package com.learning.BetIPL.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.learning.BetIPL.bean.Match;
import com.learning.BetIPL.dao.MatchDAOImpl;

public class MatchServiceImpl implements MatchService{
	
	@Autowired
	MatchDAOImpl matchDaoImpl;
	
	@Override
	public List<Match> getAllMatches() {
		return matchDaoImpl.getAllMatches();
	}

	@Override
	public List<Match> getMatchByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMatch(Match match) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateMatch(Match Match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMatch(int matchId) {
		// TODO Auto-generated method stub
		
	}

}
