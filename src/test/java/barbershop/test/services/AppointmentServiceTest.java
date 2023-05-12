package barbershop.test.services;

import barbershop.dao.entity.Appointment;
import barbershop.dao.repos.AppointmentRepository;
import barbershop.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppointmentServiceTest {
    @MockBean
    private AppointmentRepository appointmentRepository;
    @Captor
    private ArgumentCaptor<Appointment> captor;
    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Test
    public void testGetReservedTimes() {
        String date = "2023-05-09";
        String master = "Дима";

        Appointment appointment1 = new Appointment();
        appointment1.setDate(date);
        appointment1.setTime("10:00");
        appointment1.setMaster(master);

        Appointment appointment2 = new Appointment();
        appointment2.setId(2L);
        appointment2.setDate(date);
        appointment2.setTime("11:00");
        appointment2.setMaster(master);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment1);
        appointments.add(appointment2);

        when(appointmentRepository.getByDateAndMaster(date, master)).thenReturn(appointments);

        List<String> expectedTimes = new ArrayList<>();
        expectedTimes.add("10:00");
        expectedTimes.add("11:00");

        List<String> reservedTimes = appointmentService.getReservedTimes(date, master);

        Assertions.assertEquals(reservedTimes, expectedTimes);
        verify(appointmentRepository).getByDateAndMaster(date, master);
    }
}
