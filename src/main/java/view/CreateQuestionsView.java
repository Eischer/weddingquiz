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
import java.util.List;

@Named
@RequestScoped
public class CreateQuestionsView {

    private String questionDe;

    private String questionRo;

    private String[] answersDe;

    private String[] answersRo;

    private Category category;

    private List<Category> categories;

    @Inject
    private QuestionService questionService;

    @Inject
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        this.categories = categoryService.getAllCategories();
        clear();
    }

    private void clear() {
        this.answersDe = new String[] {"", "", "", "", "", ""};
        this.answersRo = new String[] {"", "", "", "", "", ""};
    }

    public String saveQuestion() {
        List<Answer> answers = new ArrayList<>();

        Category categoryToPersist = new Category(this.category.getCategoryId(), this.category.getName());
        categoryService.saveCategory(categoryToPersist);
        Question question = new Question(this.questionDe, this.questionRo, null, categoryToPersist);
        for (int i=0; i<6; i++) {
            answers.add(new Answer(answersDe[i], answersRo[i], i == 5, question));
        }
        question.setAnswers(answers);
        questionService.saveQuestion(question);
        return "/createQuestions.xhtml?faces-redirect=true";
    }

    public Category getCategoryById(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("no id");
        } else {
            for (Category currentCategory : this.categories) {
                if (categoryId.equals(currentCategory.getCategoryId())) {
                    return currentCategory;
                }
            }
            return null;
        }
    }

    public String getQuestionDe() {
        return questionDe;
    }

    public void setQuestionDe(String questionDe) {
        this.questionDe = questionDe;
    }

    public String getQuestionRo() {
        return questionRo;
    }

    public void setQuestionRo(String questionRo) {
        this.questionRo = questionRo;
    }

    public String[] getAnswersDe() {
        return answersDe;
    }

    public void setAnswersDe(String[] answersDe) {
        this.answersDe = answersDe;
    }

    public String[] getAnswersRo() {
        return answersRo;
    }

    public void setAnswersRo(String[] answersRo) {
        this.answersRo = answersRo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
