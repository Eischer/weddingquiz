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

    private Integer currentRank;

    private Integer questionsSize;

    private List<RankedPerson> allPersons;

    @Inject
    private SessionData sessionData;

    @Inject
    private PersonService personService;

    @PostConstruct
    public void init() {
        int counter=1;
        boolean showAllPersons = "1".equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show"));

        if (sessionData.getCurrentPerson() != null) {
            this.currentPerson = sessionData.getCurrentPerson();
            this.questionsSize = sessionData.getQuestionsSize();
            this.currentPerson.setRightAnswers(sessionData.getCorrectAnswsers());
            personService.savePerson(currentPerson);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        int rank = 0;
        int rightAnswersOfPreviousPerson = Integer.MAX_VALUE;
        allPersons = new ArrayList<>();
        for (Person person : personService.getAllPersons()) {
            if (person.getRightAnswers() < rightAnswersOfPreviousPerson) {
                rank = counter;
                rightAnswersOfPreviousPerson = person.getRightAnswers();
            }
            String name;
            if (rank<=10 && !showAllPersons) {
                name = "*************************";
            } else {
                name = person.getFirstName() + " " + person.getSurName();
            }
            if (showAllPersons) {
                allPersons.add(new RankedPerson(rank, name, person.getRightAnswers()));
            } else {
                if (currentPerson!= null && person.getFirstName().equals(currentPerson.getFirstName()) && person.getSurName().equals(currentPerson.getSurName())) {
                    if (rank<=10) {
                        this.currentRank = null;
                    } else {
                        this.currentRank = rank;
                    }
                }
                allPersons.add(new RankedPerson(rank, name, person.getRightAnswers()));
            }
            counter++;
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

    public Integer getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(Integer currentRank) {
        this.currentRank = currentRank;
    }
}
