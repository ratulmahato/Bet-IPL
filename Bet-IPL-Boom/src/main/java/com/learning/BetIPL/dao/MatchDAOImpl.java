package com.learning.BetIPL.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.learning.BetIPL.bean.Match;
import com.learning.BetIPL.repository.MatchRepository;

public class MatchDAOImpl implements MatchDAO{

	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}

	@Override
	public List<Match> getMatchesByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMatch(Match match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMatch(int matchId) {
		// TODO Auto-generated method stub
		
	}



}
