package view;

import model.Person;
import model.RankedPerson;
import service.PersonService;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ResultView {

    private Person currentPerson;

    private Integer questionsSize;

    private List<RankedPerson> allPersons;

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
        int rank = 1;
        this.allPersons = new ArrayList<>();
        for (Person person : personService.getAllPersons()) {
            String name;
            if (rank<=10) {
                name = "*************************";
            } else {
                name = person.getFirstName() + " " + person.getSurName();
            }
            allPersons.add(new RankedPerson(rank++, name, person.getRightAnswers()));
        }
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

    public List<RankedPerson> getAllPersons() {
        return allPersons;
    }

    public void setAllPersons(List<RankedPerson> allPersons) {
        this.allPersons = allPersons;
    }
}
