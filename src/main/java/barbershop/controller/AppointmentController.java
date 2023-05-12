package barbershop.controller;

import barbershop.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/timeslots")
    @ResponseBody
    public List<String> getReservedTimes(@RequestParam String date, @RequestParam String master) {
        return appointmentService.getReservedTimes(date, master);
    }
}