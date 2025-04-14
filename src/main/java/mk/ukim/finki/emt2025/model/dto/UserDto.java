package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.enumerations.Role;

@Data
public class UserDto {
    private String username;
    private String name;
    private String surname;
    private Role role;

    public UserDto() {
    }

    public UserDto(String username, String name, String surname, Role role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public static UserDto from(User user) {
        return new UserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }
}