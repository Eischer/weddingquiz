package view;

import model.Person;
import model.Question;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class QuizView implements Serializable {

    private Integer currentQuestionId;

    private Question currentQuestion;

    private List<Question> questions;

    @Inject
    private QuestionService questionService;

    private Person currentPerson;

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    @PostConstruct
    public void init() {
        this.questions = this.questionService.getAllQuestions();
        this.currentQuestionId = 0;
        this.currentQuestion = questions.get(this.currentQuestionId);
    }

    public String nextQuestion() {
        this.currentQuestionId++;
        if (this.currentQuestionId < this.questions.size()) {
            this.currentQuestion = questions.get(this.currentQuestionId);
            return "/quiz.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/end.xhtml?faces-redirect=true";
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(int currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }
}
