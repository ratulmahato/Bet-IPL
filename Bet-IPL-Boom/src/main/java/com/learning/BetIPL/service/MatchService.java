package com.learning.BetIPL.service;

import java.util.Date;
import java.util.List;

import com.learning.BetIPL.bean.Match;

public interface MatchService {
     List<Match> getAllMatches();
     List<Match> getMatchByDate(Date date);
     boolean addMatch(Match match);
     void updateMatch(Match Match);
     void deleteMatch(int matchId);
} 

