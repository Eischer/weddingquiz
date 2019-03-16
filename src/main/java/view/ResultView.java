package view;

import model.Person;
import model.Question;
import model.RankedPerson;
import service.PersonService;
import service.QuestionService;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Comparator;
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

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        boolean showAllPersons = "1".equals(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("show"));
        Integer schaetzQuestionRightAnswer = null;
        if (showAllPersons) {
            schaetzQuestionRightAnswer = getSchaetzQuestionRightAnswer();
        }

        if (sessionData.getCurrentPerson() != null) {
            this.currentPerson = sessionData.getCurrentPerson();
            this.questionsSize = sessionData.getQuestionsSize();
            this.currentPerson.setRightAnswers(sessionData.getCorrectAnswsers());
            personService.savePerson(currentPerson);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        int rank = 0;
        int rightAnswersOfPreviousPerson = Integer.MAX_VALUE;
        int schaetzQuestionDiffPrevious = Integer.MIN_VALUE;
        this.allPersons = new ArrayList<>();
        for (Person person : getSortedPersons(schaetzQuestionRightAnswer)) {
            String name;
            if (rank<=10 && !showAllPersons) {
                name = "*************************";
            } else {
                name = person.getFirstName() + " " + person.getSurName();
            }
            if (person.getRightAnswers() < rightAnswersOfPreviousPerson) {
                rank++;
                rightAnswersOfPreviousPerson = person.getRightAnswers();
                schaetzQuestionDiffPrevious = person.getTempSchaetzAnswerDiff();
            }
            if(person.getRightAnswers() == rightAnswersOfPreviousPerson && person.getTempSchaetzAnswerDiff() > schaetzQuestionDiffPrevious) {
                rank++;
            }
            if (showAllPersons) {
                allPersons.add(new RankedPerson(rank, name, person.getRightAnswers(), person.getSchaetzAnswer()));
            } else {
                allPersons.add(new RankedPerson(rank, name, person.getRightAnswers(), null));
            }

        }
    }

    private List<Person> getSortedPersons(Integer schaetzQuestionRightAnswer) {
        if (schaetzQuestionRightAnswer == null) {
            return personService.getAllPersons();
        }
        List<List<Person>> equalRightAnswersPerson = new ArrayList<>();
        List<Person> result = new ArrayList<>();
        List<Person> allPersons = personService.getAllPersons();
        int j = -1;
        int previousRightAnswers = Integer.MAX_VALUE;
        for (int i = 0; i < allPersons.size(); i++) {
                        allPersons.get(i).setTempSchaetzAnswerDiff(Math.abs(allPersons.get(i).getSchaetzAnswer() - schaetzQuestionRightAnswer));
            if (allPersons.get(i).getRightAnswers() < previousRightAnswers) {
                j++;
                equalRightAnswersPerson.add(new ArrayList<>());
                equalRightAnswersPerson.get(j).add(allPersons.get(i));
                previousRightAnswers = allPersons.get(i).getRightAnswers();
            } else {
                equalRightAnswersPerson.get(j).add(allPersons.get(i));
            }
        }
        for (List<Person> personsWithEqualAnswers : equalRightAnswersPerson) {
            result.addAll(sortEqualPersons(personsWithEqualAnswers));
        }
        return result;
    }

    private List<Person> sortEqualPersons(List<Person> equalRightAnswersPerson) {
        equalRightAnswersPerson.sort(Comparator.comparingInt(Person::getTempSchaetzAnswerDiff));
        return equalRightAnswersPerson;
    }

    private Integer getSchaetzQuestionRightAnswer() {
        Question schaetzfrage = questionService.getSchaetzfrage();
        if (schaetzfrage != null) {
            if (schaetzfrage.getAnswers().get(0) != null) {
                return Integer.parseInt(schaetzfrage.getAnswers().get(0).getGerman());
            }
        }
        return null;
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
