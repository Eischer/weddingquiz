package view;

import model.Person;
import service.PersonService;
import service.SessionData;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class StartView {

    private String firstName;

    private String surName;

    private String thePassword;

    private String language;

    @Inject
    private SessionData sessionData;

    @Inject
    private PersonService personService;

    public String startQuiz() {
        Person person = new Person(this.firstName, this.surName, this.language);
        personService.savePerson(person);
        sessionData.setCurrentPerson(person);
        sessionData.setLanguage(language);
        if (language == null || language.isEmpty()) {
            FacesMessage message = new FacesMessage("Wähle bitte eine Sprache aus / Selectați o limbă, va rog");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("startForm:goStartQuiz", message);
            return "";
        }
        return "/quiz.xhtml?faces-redirect=true";
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

    public String getThePassword() {
        return thePassword;
    }

    public void setThePassword(String thePassword) {
        this.thePassword = thePassword;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
