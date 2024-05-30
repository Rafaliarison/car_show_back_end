package mg.raseta.car_show.service.auth;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.auth.Validation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;

    public void sendNotification(Validation validation) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hei.manoa.4@gmail.com");
        simpleMailMessage.setTo(validation.getUser().getEmail());
        simpleMailMessage.setSubject("Votre code d'activation.");

        String text = String.format(
                "Bonjour %s <br /> Votre code d'activation est %s;",
                validation.getUser().getEmail(),
                validation.getCode()
        );

        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

    }

}