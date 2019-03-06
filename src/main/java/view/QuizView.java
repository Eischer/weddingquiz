package view;

import model.Answer;
import model.Person;
import model.Question;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;

@Named
@RequestScoped
public class QuizView implements Serializable {

    private Integer currentQuestionId;

    private Question currentQuestion;

    private Answer selectedAnswer;

    @Inject
    private SessionData sessionData;

    private static final int ORDERN_NUMBER_FOR_SCHAETZ_FRAGE = 100;

    @PostConstruct
    public void init() {
        this.currentQuestionId = sessionData.getQuestioncounter();
        this.currentQuestion = this.sessionData.getQuestions().get(this.currentQuestionId);
        Collections.shuffle(this.currentQuestion.getAnswers());
    }

    public String nextQuestion() {
        if (selectedAnswer.isCorrect()) {
            this.sessionData.incrementCorrectAnswer();
        }
        sessionData.setQuestioncounter(++this.currentQuestionId);
        if (this.currentQuestionId < this.sessionData.getQuestionsSize()) {
            if (this.sessionData.getQuestions().get(currentQuestionId).getCategory().getOrderNumber() == ORDERN_NUMBER_FOR_SCHAETZ_FRAGE) {
                return "/lastQuestion.xhtml?faces-redirect=true";
            } else {
                return "/quiz.xhtml?faces-redirect=true";
            }
        } else {
            return "/result.xhtml?faces-redirect=true";
        }
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Person getCurrentPerson() {
        return this.sessionData.getCurrentPerson();
    }
}
