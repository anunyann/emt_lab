package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String username;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}