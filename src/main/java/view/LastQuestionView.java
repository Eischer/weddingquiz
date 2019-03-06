package view;

import model.Question;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LastQuestionView implements Serializable {

    private Question currentQuestion;

    private Integer answer;

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        this.currentQuestion = questionService.getSchaetzfrage();
    }

    public String finish() {
        return "/result.xhtml?faces-redirect=true";
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
