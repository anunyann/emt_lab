package mk.ukim.finki.emt2025.web;

import mk.ukim.finki.emt2025.config.security.JwtUtils;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.dto.JwtResponse;
import mk.ukim.finki.emt2025.model.dto.UserCreateDto;
import mk.ukim.finki.emt2025.model.dto.UserDto;
import mk.ukim.finki.emt2025.model.dto.UserLoginDto;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserRestController(UserService userService,
                              AuthenticationManager authenticationManager,
                              JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto) {
        try {
            User user = userService.register(
                    userCreateDto.getUsername(),
                    userCreateDto.getPassword(),
                    userCreateDto.getRepeatPassword(),
                    userCreateDto.getName(),
                    userCreateDto.getSurname(),
                    userCreateDto.getRole()
            );

            UserDto userDto = new UserDto(
                    user.getUsername(),
                    user.getName(),
                    user.getSurname(),
                    user.getRole()
            );
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getUsername(),
                            userLoginDto.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(user);

            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            UserDto userDto = new UserDto(
                    user.getUsername(),
                    user.getName(),
                    user.getSurname(),
                    user.getRole()
            );
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.status(401).body("User not authenticated");
    }
}