package com.learning.BetIPL.dao;

import java.util.Date;
import java.util.List;

import com.learning.BetIPL.bean.Match;

public interface MatchDAO {
    List<Match> getAllMatches();
    List<Match> getMatchesByDate(Date date);
    void addMatch(Match match);
    void updateMatch(Match match);
    void deleteMatch(int matchId);
}
