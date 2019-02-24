package model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Question.allQuestions", query = "SELECT q From Question q")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    private String german;

    private String romanian;

    @ManyToOne
    @JoinColumn(name = "categoryfk")
    private Category category;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
    private List<Answer> answers;

    public Question() {
        // Default constructor for JPA
    }

    public Question(String german, String romanian, List<Answer> answers, Category category) {
        this.german = german;
        this.romanian = romanian;
        this.answers = answers;
        this.category = category;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    public String getRomanian() {
        return romanian;
    }

    public void setRomanian(String romanian) {
        this.romanian = romanian;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
