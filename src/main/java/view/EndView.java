package view;

import model.Person;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EndView {

    private Integer correctAnswers;

    private Person currentPerson;

    private Integer questionsSize;

    @Inject
    private SessionData sessionData;

    @PostConstruct
    public void init() {
        this.currentPerson = sessionData.getCurrentPerson();
        this.correctAnswers = sessionData.getCorrectAnswsers();
        this.questionsSize = sessionData.getQuestionsSize();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public Integer getCorrectAnswers() {
        return this.correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Person getCurrentPerson() {
        return this.currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public Integer getQuestionsSize() {
        return this.questionsSize;
    }
}
