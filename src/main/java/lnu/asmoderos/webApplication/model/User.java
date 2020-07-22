package lnu.asmoderos.webApplication.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tuser")
@Embeddable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String lastname;
    private String legitka;
    private int kurs;

    //@ElementCollection(targetClass = User.class, fetch = FetchType.EAGER)
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "subscribe", joinColumns = @JoinColumn(name = "id"))
//    @OrderColumn
//    private Set<Integer> subscribe;


    @Column(name = "subscribe")
    @ElementCollection(targetClass = Integer.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "subscribe", joinColumns = @JoinColumn(name = "id"))
    private List<Integer> subscribe;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ElementCollection(targetClass = Group.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Group> groups;

    public User() {

    }


    public User(int id, String email, String password, String name, String surname, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {

        this.roles = roles;
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


    public void setKurs(int kurs) {
        this.kurs = kurs;
    }

    public int getKurs() {
        return kurs;
    }


    public List<Integer> getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List<Integer> subscribe) {
        this.subscribe = subscribe;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", legitka='" + legitka + '\'' +
                ", kurs=" + kurs +
                ", groups=" + groups +
                ", roles=" + roles +
                '}';
    }
}
