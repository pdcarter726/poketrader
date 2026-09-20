package edu.ncsu.csc440.poketrader.service;

import edu.ncsu.csc440.poketrader.dto.LoginDto;
import edu.ncsu.csc440.poketrader.dto.RegisterDto;
import edu.ncsu.csc440.poketrader.dto.UserDto;
import edu.ncsu.csc440.poketrader.entity.CardCollection;
import edu.ncsu.csc440.poketrader.entity.User;
import edu.ncsu.csc440.poketrader.config.Roles.UserRoles;
import edu.ncsu.csc440.poketrader.repository.CardCollectionRepository;
import edu.ncsu.csc440.poketrader.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Peter Carter
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final CardCollectionRepository collectionRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       CardCollectionRepository collectionRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.collectionRepository = collectionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /** Register a new user and create their default collection. */
    public UserDto register(RegisterDto request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null
                || request.getFirstName() == null || request.getLastName() == null) {
            throw new IllegalArgumentException("Missing required registration fields");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setRole(UserRoles.User);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPasswordSalt(null);

        User savedUser = userRepository.save(user);

        CardCollection defaultCollection = new CardCollection();
        defaultCollection.setUser(savedUser);
        defaultCollection.setName("My Collection");
        defaultCollection.setDescription("Default collection");
        collectionRepository.save(defaultCollection);

        return new UserDto(
                savedUser.getUserId(),
                savedUser.getUsername(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRole().name()
        );
    }

    /** Validate credentials, store userId in session, return UserDTO. */
    public UserDto login(LoginDto request, HttpSession session) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Missing login credentials");
        }

        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        session.setAttribute("userId", user.getUserId());
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name()
        );
    }

    //for the CLI
    public UserDto loginDirect(LoginDto request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Missing login credentials");
        }
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return new UserDto(
                user.getUserId(), user.getUsername(),
                user.getFirstName(), user.getLastName(),
                user.getRole().name()
        );
    }

    /** Invalidate the HTTP session. */
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /** Read userId from session and return the corresponding UserDTO. */
    public UserDto getCurrentUser(HttpSession session) {
        Object userIdAttribute = session.getAttribute("userId");
        if (!(userIdAttribute instanceof Long userId)) {
            throw new IllegalStateException("No active session");
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name()
        );
    }
}
