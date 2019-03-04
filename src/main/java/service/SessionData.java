package service;

import model.Person;
import model.Question;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class SessionData implements Serializable {

    private boolean isAdmin;

    private Person currentPerson;

    private Integer correctAnswerCounter;

    private List<Question> questions;

    private String language;

    private Integer questioncounter;

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        this.questions = questionService.getAllQuestions();
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public Integer getCorrectAnswsers() {
        return this.correctAnswerCounter;
    }

    public void setCorrectAnswsers(Integer correctAnswerCounter) {
        this.correctAnswerCounter = correctAnswerCounter;
    }

    public void incrementCorrectAnswer() {
        ++this.correctAnswerCounter;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getQuestionsSize() {
        return this.questions.size();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isGerman() {
        return this.language.equals("Deutsch");
    }

    public Integer getQuestioncounter() {
        if (questioncounter == null) {
            return 0;
        }
        return questioncounter;
    }

    public void setQuestioncounter(Integer questioncounter) {
        this.questioncounter = questioncounter;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
