package barbershop.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "session_id")
    private String sessionID;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_appointments", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "appointment_id"))
    private List<Appointment> appointments;

    public User() {
    }

    public User(String login, String password, String phone, String name, String email) {
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
    }

    public User(String password, Collection<Role> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }
}
