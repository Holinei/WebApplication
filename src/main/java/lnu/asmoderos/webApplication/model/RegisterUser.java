package lnu.asmoderos.webApplication.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

public class RegisterUser {

    private String email;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
    private String lastname;
    private String legitka;
    private int kurs;
    private String groupString;

    public String getGroupString() {
        return groupString;
    }

    public void setGroupString(String groupString) {
        this.groupString = groupString;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    private Set<Group> groups;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLegitka() {
        return legitka;
    }

    public void setLegitka(String legitka) {
        this.legitka = legitka;
    }

    public int getKurs() {
        return kurs;
    }

    public void setKurs(int kurs) {
        this.kurs = kurs;
    }
}
