package view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class StartView {

    private String language;

    private String firstname;

    private String surname;

    private String password;

    public String validateLogin() {
//        if (userService.validateUser(this.username, this.password) != null) {
//            this.currentUser.setUser(userService.getUserByName(this.username));
//            return "/admin/teams.xhtml";
//        } else {
//            return "/login.xhtml";
//        }
        return "start.xhtml";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
