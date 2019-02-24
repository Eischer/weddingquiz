package service;


import model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryService {

    @PersistenceContext(unitName = "weddingQuizPU")
    private EntityManager entityManager;

    public void saveCategory(Category category) {
        entityManager.persist(category);
    }

    public List<Category> getAllCategories () {
        return entityManager.createNamedQuery("Category.allCategories", Category.class).getResultList();
    }

    public void update (Category category) {
        entityManager.merge(category);
    }
}
