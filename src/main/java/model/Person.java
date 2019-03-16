package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Person.getPersonByName", query = "SELECT p From Person P WHERE p.firstName = :firstName AND p.surName = :surName"),
        @NamedQuery(name = "Person.getAllPersons", query = "SELECT p From Person P order by p.rightAnswers DESC")})
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    private String firstName;

    private String surName;

    private String language;

    private int rightAnswers;

    private int schaetzAnswer;

    @Transient
    private int tempSchaetzAnswerDiff;

    public Person() {
        // default constructor for hibernate
    }

    public Person (String firstName, String surName, String language) {
        this.firstName = firstName;
        this.surName = surName;
        this.language = language;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public int getSchaetzAnswer() {
        return schaetzAnswer;
    }

    public void setSchaetzAnswer(int schaetzAnswer) {
        this.schaetzAnswer = schaetzAnswer;
    }

    public int getTempSchaetzAnswerDiff() {
        return tempSchaetzAnswerDiff;
    }

    public void setTempSchaetzAnswerDiff(int tempSchaetzAnswerDiff) {
        this.tempSchaetzAnswerDiff = tempSchaetzAnswerDiff;
    }
}
