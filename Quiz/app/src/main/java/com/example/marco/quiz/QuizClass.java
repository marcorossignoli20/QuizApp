package com.example.marco.quiz;

/**
 * Created by Marco on 23/01/2017.
 */

public class QuizClass {
    private String question;
    private String answer;

    public QuizClass(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
