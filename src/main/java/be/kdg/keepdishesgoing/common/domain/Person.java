package be.kdg.keepdishesgoing.common.domain;

import java.util.UUID;

public class Person {
    private PersonId personId;
    private String username;
    private String password;
    private String email;
    private Role role;

    public PersonId getPersonId() {
        return personId;
    }

    public void setPersonId(PersonId personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
