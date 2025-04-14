package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt2025.model.dto.UserCreateDto;
import mk.ukim.finki.emt2025.model.dto.UserDto;
import mk.ukim.finki.emt2025.model.dto.UserLoginDto;
import mk.ukim.finki.emt2025.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.emt2025.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and management")
public class UserRestController {

    private final UserApplicationService userService;

    public UserRestController(UserApplicationService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateDto userCreateDto) {
        try {
            return this.userService.register(userCreateDto)
                    .map(user -> ResponseEntity.ok(user))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and returns user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            return this.userService.login(userLoginDto)
                    .map(user -> ResponseEntity.ok(user))
                    .orElseGet(() -> ResponseEntity.status(401).build());
        } catch (InvalidUserCredentialsException exception) {
            return ResponseEntity.status(401).build();
        }
    }

    @Operation(summary = "Get current user", description = "Retrieves the currently authenticated user's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(HttpServletRequest request) {
        String username = request.getRemoteUser();
        if (username == null) {
            return ResponseEntity.notFound().build();
        }

        return this.userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "User logout", description = "Logs out the current user")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }
}