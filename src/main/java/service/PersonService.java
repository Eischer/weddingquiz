package service;

import model.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonService {

    @PersistenceContext(unitName = "weddingQuizPU")
    protected EntityManager entityManager;

    public void savePerson(Person person) {
        entityManager.persist(person);
    }

    public void update (Person person) {
        entityManager.merge(person);
    }
}
