package view;

import model.Answer;
import model.Person;
import model.Question;
import service.QuestionService;
import service.SessionData;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;

@Named
@SessionScoped
public class QuizView implements Serializable {

    private Integer currentQuestionId;

    private Question currentQuestion;

    private Answer selectedAnswer;

    @Inject
    private SessionData sessionData;

    @PostConstruct
    public void init() {
        this.sessionData.setCorrectAnswsers(0);
        this.currentQuestionId = 0;
        this.currentQuestion = this.sessionData.getQuestions().get(this.currentQuestionId);
        Collections.shuffle(this.currentQuestion.getAnswers());
    }

    public String nextQuestion() {
        if (selectedAnswer.isCorrect()) {
            this.sessionData.incrementCorrectAnswer();
        }

        this.currentQuestionId++;
        if (this.currentQuestionId < this.sessionData.getQuestionsSize()) {
            this.currentQuestion = this.sessionData.getQuestions().get(this.currentQuestionId);
            Collections.shuffle(this.currentQuestion.getAnswers());
            return "/quiz.xhtml?faces-redirect=true";
        } else {
            return "/end.xhtml?faces-redirect=true";
        }
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
