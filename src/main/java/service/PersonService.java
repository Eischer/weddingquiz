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

    public boolean hasNotAlreadyParticipated(String firstName, String surName) {
       return entityManager.createNamedQuery("Person.getPersonByName")
                .setParameter("firstName", firstName)
                .setParameter("surName", surName)
                .getResultList().isEmpty();
    }
}
