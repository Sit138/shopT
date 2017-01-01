package entity.security;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// TODO: Kirill если используешь название сущности в hql лучше его явно указать здесь
@Entity(name = "Role")
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> users = new HashSet<User>();



}
