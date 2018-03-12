package com.learning.BetIPL.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Matches")
public class Match implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String home_team;
    @Column
    private String away_team;
    @Column
    private String stadium;
    @Column
    private String result;
    @Column
    private String desc;
    @Column
    private String man_of_match;
    @Column
    private Date date;
    
    

    

    // Getters and Setters ... (Omitted for brevity)
}