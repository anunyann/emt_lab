package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.UserCreateDto;
import mk.ukim.finki.emt2025.model.dto.UserDto;
import mk.ukim.finki.emt2025.model.dto.UserLoginDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<UserDto> register(UserCreateDto userCreateDto);
    Optional<UserDto> login(UserLoginDto userLoginDto);
    Optional<UserDto> findByUsername(String username);
}