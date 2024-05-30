package mg.raseta.car_show.service.auth;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.User;
import mg.raseta.car_show.model.auth.Validation;
import mg.raseta.car_show.repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void save(User user) {
        Validation validation = new Validation();
        validation.setUser(user);

        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);

        validationRepository.save(validation);
        notificationService.sendNotification(validation);
    }

    public Validation readAccordingCode(String code) {
        return validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Code does not match try again."));
    }

}