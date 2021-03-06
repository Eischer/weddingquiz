package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    private String german;

    private String romanian;

    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "questionFk")
    private Question question;

    public Answer() {
        // Default constructor for JPA
    }

    public Answer(String german, String romanian, boolean correct, Question question) {
        this.german = german;
        this.romanian = romanian;
        this.correct = correct;
        this.question = question;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public String getRomanian() {
        return romanian;
    }

    public void setRomanian(String romanian) {
        this.romanian = romanian;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
