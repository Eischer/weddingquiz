package view;

import model.Answer;
import model.Category;
import model.Question;
import service.CategoryService;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class CreateQuestionsView {

    private String question_de;

    private String question_ro;

    private String[] answers_de;

    private String[] answers_ro;

    private String category;

    private Map<Long,String> categories;

    @Inject
    private QuestionService questionService;

    @Inject
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        this.answers_de = new String[] {"", "", "", "", "", ""};
        this.answers_ro = new String[] {"", "", "", "", "", ""};
        this.categories = new HashMap<>();
        for (Category category: categoryService.getAllCategories()) {
           this.categories.put(category.getCategoryId(), category.getName());
        }
    }

    public void saveQuestion() {
        List<Answer> answers = new ArrayList<>();

        Category category = getCorrespondingCategory();
        categoryService.saveCategory(category);
        Question question = new Question(this.question_de, this.question_ro, null, category);
        for (int i=0; i<6; i++) {
            answers.add(new Answer(answers_de[i], answers_ro[i], i == 5, question));
        }
        question.setAnswers(answers);
        questionService.saveQuestion(question);
        category.getQuestions().add(question);
        categoryService.update(category);

    }

    private Category getCorrespondingCategory() {
        for (Map.Entry<Long, String> category : this.categories.entrySet()) {
            if (category.getValue().equals(this.category)) {
                return new Category(category.getKey(), category.getValue());
            }
        }
        return null;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<Long, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<Long, String> categories) {
        this.categories = categories;
    }
}
