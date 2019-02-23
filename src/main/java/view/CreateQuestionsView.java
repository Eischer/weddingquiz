package view;

import model.Answer;
import model.Question;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CreateQuestionsView {

    private String question_de;

    private String question_ro;

    private String[] answers_de;

    private String[] answers_ro;

    @Inject
    private QuestionService questionService;

    @PostConstruct
    public void init() {
        this.answers_de = new String[] {"", "", "", "", "", ""};
        this.answers_ro = new String[] {"", "", "", "", "", ""};
    }

    public void saveQuestion() {
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(this.question_de, this.question_ro, null);
        for (int i=0; i<6; i++) {
            answers.add(new Answer(answers_de[i], answers_ro[i], i == 5, question));
        }
        question.setAnswers(answers);

        questionService.saveQuestion(question);
    }

    public String getQuestion_de() {
        return question_de;
    }

    public void setQuestion_de(String question_de) {
        this.question_de = question_de;
    }

    public String getQuestion_ro() {
        return question_ro;
    }

    public void setQuestion_ro(String question_ro) {
        this.question_ro = question_ro;
    }

    public String[] getAnswers_de() {
        return answers_de;
    }

    public void setAnswers_de(String[] answers_de) {
        this.answers_de = answers_de;
    }

    public String[] getAnswers_ro() {
        return answers_ro;
    }

    public void setAnswers_ro(String[] answers_ro) {
        this.answers_ro = answers_ro;
    }
}
