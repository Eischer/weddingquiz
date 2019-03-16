package service;

import model.Question;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class QuestionService {

    @PersistenceContext(unitName = "weddingQuizPU")
    private EntityManager entityManager;

    public List<Question> getAllQuestions() {
        return entityManager.createNamedQuery("Question.allQuestions", Question.class).getResultList();
    }

    public void saveQuestion(Question question) {
        entityManager.persist(question);
    }
}
