package com.learning.BetIPL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.BetIPL.bean.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long>{

}
