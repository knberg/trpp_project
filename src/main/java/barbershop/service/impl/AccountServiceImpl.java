package barbershop.service.impl;

import barbershop.dao.entity.Appointment;
import barbershop.dao.entity.User;
import barbershop.dao.repos.AppointmentRepository;
import barbershop.dao.repos.UserRepository;
import barbershop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private UserRepository userRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, AppointmentRepository appointmentRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public List<Appointment> getAppointments() {
        User user = getUser();
        return user.getAppointments();
    }

    @Override
    @Transactional
    public void addAppointment(Appointment appointment) {
        User user = getUser();
        user.addAppointment(appointment);
        appointmentRepository.save(appointment);
    }

    @Override
    @Transactional
    public void deleteAppointment(String id) {
        User user = getUser();
        Appointment appointment = appointmentRepository.getById(Long.valueOf(id));
        user.removeAppointment(appointment);
        appointmentRepository.delete(appointment);
    }

    @Override
    public void updateUser(String name, String phone, String email) {
        User user = getUser();
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        userRepository.save((user));
    }

    @Override
    public User getUser() {
        String username;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        } catch (NullPointerException e){
            username = "test";
        }
        System.out.println(username);
        return userRepository.getByLogin(username);
    }
}
