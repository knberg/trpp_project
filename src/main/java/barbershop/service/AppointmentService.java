package barbershop.service;

import java.util.List;

public interface AppointmentService {

    List<String> getReservedTimes(String date, String master);
}
