package model;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    private String german;

    private String romanian;

    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "questionFk")
    private Question question;

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
}
