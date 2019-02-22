package view;

import model.Person;
import service.PersonService;
import service.SessionData;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class StartView {

    private String language;

    private String firstName;

    private String surName;

    private String password;

    @Inject
    private SessionData sessionData;

    @Inject
    private PersonService personService;

    public String startQuiz() {
        Person person = new Person(this.firstName, this.surName, this.language);
        personService.savePerson(person);
        sessionData.setCurrentPerson(person);
        return "/quiz.xhtml";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
