package net.heimeng.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
public class MillDTO {

    private String message;

    @JsonPropertyDescription("Optional")
    private Question question;

    @JsonPropertyDescription("The progress of game, from 0 to 100. It is 0 by default." +
            " The game has 5 levels, corresponding with progress 20, 40, 60, 80, 100." +
            " If player's answer is incorrect, the progress will be reset to 0, and game over.")
    private Integer progress;

    public MillDTO() {
    }

    public MillDTO(String message, Question question, Integer progress) {
        this.message = message;
        this.question = question;
        this.progress = progress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    @Override
    public String toString() {
        return "MillDTO{" +
                "message='" + message + '\'' +
                ", question=" + question +
                ", progress=" + progress +
                '}';
    }
}
