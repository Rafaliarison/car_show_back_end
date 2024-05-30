package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}