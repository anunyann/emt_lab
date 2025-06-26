package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.dto.UserCreateDto;
import mk.ukim.finki.emt2025.model.dto.UserDto;
import mk.ukim.finki.emt2025.model.dto.UserLoginDto;
import mk.ukim.finki.emt2025.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserApplicationServiceImpl(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<UserDto> register(UserCreateDto userCreateDto) {
        User user = userService.register(
                userCreateDto.getUsername(),
                userCreateDto.getPassword(),
                userCreateDto.getRepeatPassword(),
                userCreateDto.getName(),
                userCreateDto.getSurname(),
                userCreateDto.getRole()
        );
        return Optional.of(UserDto.from(user));
    }

    @Override
    public Optional<UserDto> login(UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getUsername(),
                            userLoginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = this.userService.findByUsername(userLoginDto.getUsername());
            return Optional.of(UserDto.from(user));
        } catch (Exception e) {
            throw new InvalidUserCredentialsException();
        }
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        User user = this.userService.findByUsername(username);
        return Optional.of(UserDto.from(user));
    }
}