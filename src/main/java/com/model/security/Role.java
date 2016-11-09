package com.model.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO: Kirill если используешь название сущности в hql лучше его явно указать здесь
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private int id;

    @Column(name = "name_role")
    @Getter @Setter
    private String nameRole;

    // TODO: Kirill странный мапинг коллекции, странный каскад  
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    @Getter @Setter
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        user.setRole(this);
        getUsers().add(user);
    }

}
