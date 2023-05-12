package barbershop.controller;

import barbershop.dao.entity.User;
import barbershop.dao.entity.UserRegistration;
import barbershop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @ModelAttribute("user")
    public UserRegistration userRegistrationDto() {
        return new UserRegistration();
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistration userDto,
                                      BindingResult result, @CookieValue("JSESSIONID") String cookie) {

        User existing = userService.getByLogin(userDto.getLogin());
        if (existing != null) {
            result.rejectValue("login", "", "Этот логин занят");
            return "registration";
        }
        userService.createUser(userDto, cookie);
        return "redirect:/account";
    }
}
