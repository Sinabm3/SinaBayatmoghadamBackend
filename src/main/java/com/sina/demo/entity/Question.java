package com.sina.demo.entity;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String text;
    @Column (nullable = false)
    private boolean isHorizontalQuestion;
    @Column (nullable = false)
    private int axisPower; // Impact on the horizontal axis
    @ManyToOne()
    private CompassGame compassGame;


    public Question(Long id, String text, boolean isHorizontalQuestion, int axisPower, CompassGame compassGame) {
        this.id = id;
        this.text = text;
        this.isHorizontalQuestion = isHorizontalQuestion;
        this.axisPower = axisPower;
        this.compassGame = compassGame;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isHorizontalQuestion() {
        return isHorizontalQuestion;
    }

    public int getAxisPower() {
        return axisPower;
    }

    public Question() {

    }
}
