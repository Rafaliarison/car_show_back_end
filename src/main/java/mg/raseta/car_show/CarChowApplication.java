package mg.raseta.car_show;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "mg.raseta.car_show.repository")
public class CarChowApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarChowApplication.class, args);
    }

}
