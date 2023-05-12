package barbershop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/price")
    public String getPrice() {
        return "price";
    }

    @GetMapping("/gallery")
    public String getGallery() {
        return "gallery";
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "contacts";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
