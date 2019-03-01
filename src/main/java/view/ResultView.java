package view;

import model.Person;
import service.PersonService;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ResultView {

    private Person currentPerson;

    private Integer questionsSize;

    private List<Person> allPersons;

    @Inject
    private SessionData sessionData;

    @Inject
    private PersonService personService;

    @PostConstruct
    public void init() {
        if (sessionData.getCurrentPerson() != null) {
            this.currentPerson = sessionData.getCurrentPerson();
            this.questionsSize = sessionData.getQuestionsSize();
            this.currentPerson.setRightAnswers(sessionData.getCorrectAnswsers());
            personService.savePerson(currentPerson);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        this.allPersons = personService.getAllPersons();
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

    public List<Person> getAllPersons() {
        return allPersons;
    }

    public void setAllPersons(List<Person> allPersons) {
        this.allPersons = allPersons;
    }
}
