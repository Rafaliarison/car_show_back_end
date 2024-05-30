package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.Car;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaSpecificationExecutor<Car>, GenericRepository<Car, Integer> {}