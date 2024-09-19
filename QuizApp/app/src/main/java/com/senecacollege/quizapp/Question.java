package com.senecacollege.quizapp;

public class Question {
    private String question1 = "What is 2 x 5";
    private String question2 = "What is 2 + 2?";
    private String question3 = "What is 4 - 2?";

    private int numOfQuestions = 3;
    private int numOfCorrectAnswers = 0;
    private int currentQuestion = 1;

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public int getNumOfCorrectAnswers() {
        return numOfCorrectAnswers;
    }

    public void setNumOfCorrectAnswers(int numOfCorrectAnswers) {
        this.numOfCorrectAnswers = numOfCorrectAnswers;
    }
}
