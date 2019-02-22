package view;

import model.Person;
import service.PersonService;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EndView {

    private Person currentPerson;

    private Integer questionsSize;

    @Inject
    private SessionData sessionData;

    @Inject
    private PersonService personService;

    @PostConstruct
    public void init() {
        this.currentPerson = sessionData.getCurrentPerson();
        this.questionsSize = sessionData.getQuestionsSize();
        this.currentPerson.setRightAnswers(sessionData.getCorrectAnswsers());
        personService.update(currentPerson);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
