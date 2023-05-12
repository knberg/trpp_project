package barbershop.controller;

import barbershop.dao.entity.Appointment;
import barbershop.dao.entity.User;
import barbershop.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public ModelAndView getAccount() {
        ModelAndView modelAndView = new ModelAndView("account");
        User user = accountService.getUser();
        List<Appointment> appointments = user.getAppointments();
        modelAndView.addObject("user", user);
        modelAndView.addObject("appointments", appointments);
        return modelAndView;
    }

    @PostMapping("/account/user")
    public String updateAccount(@ModelAttribute("user") User user) {
        accountService.updateUser(user.getName(), user.getPhone(), user.getEmail());
        return "redirect:/account";
    }

    @PostMapping("/account")
    public String addAppointment(@ModelAttribute("appointment") Appointment appointment) {
        appointment.setClient(accountService.getUser().getName());
        accountService.addAppointment(appointment);
        return "redirect:/account";
    }

    @GetMapping("/account/delete/{id}")
    public String deleteAppointment(@PathVariable String id) {
        accountService.deleteAppointment(id);
        return "redirect:/account";
    }
}
