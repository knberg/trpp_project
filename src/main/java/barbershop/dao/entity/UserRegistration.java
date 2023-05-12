package barbershop.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegistration {
    @NotNull
    private String login;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
}
