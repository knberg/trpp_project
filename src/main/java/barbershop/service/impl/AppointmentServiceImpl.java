package barbershop.service.impl;

import barbershop.dao.entity.Appointment;
import barbershop.dao.repos.AppointmentRepository;
import barbershop.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<String> getReservedTimes(String date, String master) {
        List<Appointment> appointments = appointmentRepository.getByDateAndMaster(date, master);
        List<String> reservedTimes = new ArrayList<>();
        for (Appointment a : appointments) {
            reservedTimes.add(a.getTime());
        }
        return reservedTimes;
    }
}


