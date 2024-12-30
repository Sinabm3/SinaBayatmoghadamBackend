package com.sina.demo.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class CompassGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column (nullable = false, length = 100)
    private String horizontalAxisPositiveName;
    @Column (nullable = false, length = 100)
    private String horizontalAxisNegativeName;
    @Column (nullable = false, length = 100)
    private String verticalAxisPositiveName;
    @Column (nullable = false, length = 100)
    private String verticalAxisNegativeName;
    @OneToMany(mappedBy = "compassGame", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHorizontalAxisPositiveName() {
        return horizontalAxisPositiveName;
    }

    public void setHorizontalAxisPositiveName(String horizontalAxisName) {
        this.horizontalAxisPositiveName = horizontalAxisName;
    }

    public String getVerticalAxisPositiveName() {
        return verticalAxisPositiveName;
    }

    public void setVerticalAxisPositiveName(String verticalAxisName) {
        this.verticalAxisPositiveName = verticalAxisName;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public String getHorizontalAxisNegativeName() {
        return horizontalAxisNegativeName;
    }

    public void setHorizontalAxisNegativeName(String horizontalAxisNegativeName) {
        this.horizontalAxisNegativeName = horizontalAxisNegativeName;
    }

    public String getVerticalAxisNegativeName() {
        return verticalAxisNegativeName;
    }

    public void setVerticalAxisNegativeName(String verticalAxisNegativeName) {
        this.verticalAxisNegativeName = verticalAxisNegativeName;
    }
}
