package view;

import model.Person;
import service.PersonService;
import service.QuestionService;
import service.SessionData;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@RequestScoped
public class StartView {

    private String firstName;

    private String surName;

    private String thePassword;

    private String language;

    @Inject
    private PersonService personService;

    @Inject
    private QuestionService questionService;

    @Inject
    private SessionData sessionData;

    public String startQuiz() {
        resetSession();
        if (language == null || language.isEmpty()) {
            FacesMessage message = new FacesMessage("Wähle bitte eine Sprache aus / Selectați o limbă, va rog");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("startForm:goStartQuiz", message);
            return "";
        }
        if (isAdmin()) {
            return "/createCategory.xhtml?faces-redirect=true";
        }
        if (personService.hasNotAlreadyParticipated(this.firstName, this.surName)) {
            if (questionService.getAllQuestions().isEmpty()) {
                return "/result.xhtml?faces-redirect=true";
            } else {
                return "/quiz.xhtml?faces-redirect=true";
            }
        } else {
            FacesMessage message = new FacesMessage(this.firstName +  " " + this.surName + ", du hast bereits teilgenommen. / ați participat deja.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("startForm:surName", message);
            return "";
        }
    }

    private void resetSession() {
        if (sessionData.getCurrentPerson() != null) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        Person person = new Person(this.firstName, this.surName, this.language);
        sessionData.setCurrentPerson(person);
        sessionData.setLanguage(language);
        sessionData.setCorrectAnswsers(0);
    }

    private boolean isAdmin() {
        byte[] bytesOfPassword = thePassword.getBytes();
        MessageDigest md5 = null;
        StringBuilder md5Password = new StringBuilder();
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md5.digest(bytesOfPassword);
        for (byte b : digest) {
            md5Password.append(String.format("%02x", b & 0xff));
        }
        if (md5Password.toString().equals("48f16aeda181f110f682ca8a24d37265")) {
            return true;
        }
        return false;
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
