package barbershop.service;

import barbershop.dao.entity.Appointment;
import barbershop.dao.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountService {

    User getUser();

    @Transactional
    List<Appointment> getAppointments();

    @Transactional
    void addAppointment(Appointment appointment);

    void deleteAppointment(String name);

    void updateUser(String name, String phone, String email);
}
