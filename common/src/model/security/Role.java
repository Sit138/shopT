package model.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO: Kirill если используешь название сущности в hql лучше его явно указать здесь
@Entity
@Table(name = "role")
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(name = "name_role")
    private String nameRole;

    // TODO: Kirill странный мапинг коллекции, странный каскад
    // решил оставить, я добавляю юзеру роль и сохраняю роль, потому каскад (можно обсудить)
    // ну так приходи со своими "можно обсудить" сразу и потом удаляй отсюда это все. а то какие то диалоги получаются
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user){
        user.setRole(this);
        getUsers().add(user);
    }

}
