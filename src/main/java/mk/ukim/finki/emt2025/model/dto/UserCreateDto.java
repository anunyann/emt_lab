package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.enumerations.Role;

@Data
public class UserCreateDto {
    private String username;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
    private Role role;

    public UserCreateDto() {
    }

    public UserCreateDto(String username, String password, String repeatPassword, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
}
