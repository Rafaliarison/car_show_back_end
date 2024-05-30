package mg.raseta.car_show.service.auth;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.User;
import mg.raseta.car_show.model.auth.Role;
import mg.raseta.car_show.model.auth.Validation;
import mg.raseta.car_show.model.enums.Permission;
import mg.raseta.car_show.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;

    public void createUser(User user) {

        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new RuntimeException("E-mail as not valid.");
        }

        if (user.getPassword().length() < 8) {
            throw new RuntimeException("Password must contain 8 characters minimum.");
        }

        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Your e-mail is already register.");
        }

        // Encode user password and make this as the new password that saving in database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role userRole = new Role();
        userRole.setRoleName(Permission.CLIENT);
        user.setRole(userRole);

        user = this.userRepository.save(user);
        validationService.save(user);
    }

    public void activate(Map<String, String> activation) {
        Validation validation = this.validationService.readAccordingCode(activation.get("code"));

        if (Instant.now().isAfter(validation.getExpiration())) {
            throw new RuntimeException("Your activation code is expired.");
        }

        User userActivated = this.userRepository.findById(validation.getUser().getUserId()).orElseThrow(() -> new RuntimeException("User not recognized"));
        userActivated.setActive(true);
        this.userRepository.save(userActivated);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user match."));
    }

}