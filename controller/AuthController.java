package edu.ncsu.csc440.poketrader.controller;

import edu.ncsu.csc440.poketrader.dto.LoginDto;
import edu.ncsu.csc440.poketrader.dto.RegisterDto;
import edu.ncsu.csc440.poketrader.dto.UserDto;
import edu.ncsu.csc440.poketrader.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Implemented by Alex Judd.
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto request) {
        // call authService.register, return 201
        try {
            final UserDto response = authService.register(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing required registration fields")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (e.getMessage().equals("Username already exists")) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                throw new IllegalArgumentException("Something bad happened.");
            }
        }
    }

    /**
     * [TODO] find out if this really should return a UserDTO.
     * Implemented by Alex Judd.
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto request, HttpSession session) {
        // call authService.login, return 200 with UserDTO or 401
        try {
            final UserDto response = authService.login(request, session);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("Missing login credentials")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (e.getMessage().equals("Invalid username or password")) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Implemented by Alex Judd.
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        // [OLD STUB] call authService.logout, return 204
        authService.logout(session);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Returns the currently logged-in user based on the session cookie.
     * Implemented by Alex Judd.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(HttpSession session) {
        // [OLD STUB] call authService.getCurrentUser, return 200 or 401 if no session
        try {
            final UserDto response = authService.getCurrentUser(session);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("No active session") ||
                    e.getMessage().equals("User not found")) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
