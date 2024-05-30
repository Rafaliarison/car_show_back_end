package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.auth.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}