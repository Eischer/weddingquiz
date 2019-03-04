package model;

import java.io.Serializable;

public class RankedPerson implements Serializable {

    private int rank;

    private String name;

    private int rightAnswers;

    public RankedPerson(int rank, String name, int rightAnswers) {
        this.rank = rank;
        this.name = name;
        this.rightAnswers = rightAnswers;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }
}
