package barbershop.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "client")
    String client;
    @Column(name = "master")
    String master;
    @Column(name = "date")
    String date;
    @Column(name = "time")
    String time;
}
