package com.model.security;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO: Kirill если используешь название сущности в hql лучше его явно указать здесь
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_role")
    private String nameRole;

    // TODO: Kirill странный мапинг коллекции, странный каскад  
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public int getId() {
        return id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        user.setRole(this);
        getUsers().add(user);
    }

}
