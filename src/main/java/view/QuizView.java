package view;

import model.Person;
import model.Question;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class QuizView implements Serializable {

    private Person currentPerson;

    private List<Question> questions;

    private Question currentQuestion;

    @Inject
    private QuestionService questionService;

    private int questionCounter;

    @PostConstruct
    public void init() {
        questions = questionService.getAllQuestions();
        this.questionCounter = 0;
        currentQuestion = questions.get(questionCounter);
    }

    public String nextQuestion() {
        if (this.questionCounter < this.questions.size()) {
            questionCounter++;
            currentQuestion = questions.get(questionCounter);
            return "/quiz.xhml";
        }
        return "/end.xhtml";
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
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

    public int getQuestionCounter() {
        return questionCounter;
    }

    public void setQuestionCounter(int questionCounter) {
        this.questionCounter = questionCounter;
    }
}
