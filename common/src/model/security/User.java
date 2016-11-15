package model.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    // TODO: Kirill ну конечно юзер нейм
    @Column(name = "username")
    @Getter
    @Setter
    private String userName;

    // TODO: Kirill а это тогда юзер пассворд
    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @Column(name = "enabled")
    @Getter
    @Setter
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Getter
    @Setter
    private Role role;

    public boolean isEnabled() {
        return enabled;
    }
}

