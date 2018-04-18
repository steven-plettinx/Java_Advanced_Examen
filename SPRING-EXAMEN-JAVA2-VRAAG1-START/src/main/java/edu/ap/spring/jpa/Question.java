package edu.ap.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String question;

    @Column
    private String answer;

    public Question() {}

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return this.question; }

    public  String getAnswer() { return this.answer; }

}
