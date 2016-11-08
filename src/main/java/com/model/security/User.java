package com.model.security;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // TODO: Kirill ну конечно юзер нейм
    @Column(name = "username")
    private String userName;

    // TODO: Kirill а это тогда юзер пассворд
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
