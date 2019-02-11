package view;

import model.Question;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class QuizView implements Serializable {

    private Integer currentQuestionId;

    private Question currentQuestion;

    private List<Question> questions;

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        this.questions = this.questionService.getAllQuestions();

        String currentQuestionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("question_id");
        if (this.currentQuestionId == null) {
            this.currentQuestionId = 0;
        } else {
            this.currentQuestionId = Integer.parseInt(currentQuestionId);
        }
        this.currentQuestion = questions.get(this.currentQuestionId);
    }

    public String nextQuestion() {
        currentQuestionId++;
        if (this.currentQuestionId < this.questions.size()) {
            currentQuestion = questions.get(currentQuestionId);
            return "/quiz.xhtml";
        }
        return "/end.xhtml";
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
