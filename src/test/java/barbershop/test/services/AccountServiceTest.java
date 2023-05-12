package barbershop.test.services;

import barbershop.dao.entity.Appointment;
import barbershop.dao.entity.User;
import barbershop.dao.repos.AppointmentRepository;
import barbershop.dao.repos.UserRepository;
import barbershop.service.impl.AccountServiceImpl;
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
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountServiceTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AppointmentRepository apmntRepository;
    @Captor
    private ArgumentCaptor<Appointment> captor;
    @Captor
    private ArgumentCaptor<User> userCaptor;
    private AccountServiceImpl accountService;

    @Autowired
    public void setBasketService(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @BeforeEach
    public void init(){
        User user = new User();
        user.setLogin("test");
        user.setPassword("password");
        user.setName("test");
        user.setPhone("test");
        user.setEmail("test");
        user.setId(1L);
        user.setAppointments(new ArrayList<>());
        when(userRepository.getByLogin(user.getLogin())).thenReturn(user);
    }

    @Test
    public void getAppointments() {
        Appointment apmnt1 = new Appointment();
        apmnt1.setId(1L);
        apmnt1.setClient("Test");
        apmnt1.setMaster("Дима");
        apmnt1.setDate("2023-05-20");
        apmnt1.setTime("10:00");

        Appointment apmnt2 = new Appointment();
        apmnt2.setId(2L);
        apmnt2.setClient("Test");
        apmnt2.setMaster("Антон");
        apmnt2.setDate("2023-08-12");
        apmnt2.setTime("12:30");

        accountService.addAppointment(apmnt1);
        accountService.addAppointment(apmnt2);

        List<Appointment> fetched = accountService.getAppointments();
        User user = userRepository.getByLogin("test");
        Assertions.assertEquals(user.getAppointments(), fetched);
    }

    @Test
    public void addAppointment(){
        Appointment apmnt = new Appointment();
        apmnt.setId(1L);
        apmnt.setClient("Test");
        apmnt.setMaster("Дима");
        apmnt.setDate("2023-05-20");
        apmnt.setTime("10:00");

        accountService.addAppointment(apmnt);

        verify(apmntRepository).save(captor.capture());
        Appointment captured = captor.getValue();
        Assertions.assertEquals(apmnt.getId(), captured.getId());
        Assertions.assertEquals(apmnt.getClient(), captured.getClient());
        Assertions.assertEquals(apmnt.getMaster(), captured.getMaster());
        Assertions.assertEquals(apmnt.getDate(), captured.getDate());
        Assertions.assertEquals(apmnt.getTime(), captured.getTime());

        User user = userRepository.getByLogin("test");
        Assertions.assertNotEquals(user.getAppointments().indexOf(apmnt),-1);
    }

    @Test
    public void deleteAppointment(){
        Appointment apmnt = new Appointment();
        apmnt.setId(1L);
        apmnt.setClient("Test");
        apmnt.setMaster("Дима");
        apmnt.setDate("2023-05-20");
        apmnt.setTime("10:00");

        accountService.addAppointment(apmnt);

        verify(apmntRepository).save(captor.capture());
        Appointment captured = captor.getValue();

        when(apmntRepository.getById(apmnt.getId())).thenReturn(apmnt);
        accountService.deleteAppointment("1");

        Assertions.assertEquals(accountService.getAppointments().indexOf(captured), -1);
    }


    @Test
    public void testUpdateUser() {
        String name = "John Doe";
        String phone = "1234567890";
        String email = "john.doe@example.com";

        User user = new User();
        user.setId(1L);
        user.setName("Jane Smith");
        user.setPhone("0987654321");
        user.setEmail("jane.smith@example.com");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        accountService.updateUser(name, phone, email);

        verify(userRepository).findById(user.getId());
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();

        Assertions.assertEquals(name, capturedUser.getName());
        Assertions.assertEquals(phone, capturedUser.getPhone());
        Assertions.assertEquals(email, capturedUser.getEmail());
    }
}
