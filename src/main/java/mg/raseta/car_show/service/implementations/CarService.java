package mg.raseta.car_show.service.implementations;

import mg.raseta.car_show.model.Car;
import mg.raseta.car_show.repository.CarRepository;
import mg.raseta.car_show.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService extends GenericService<Car, Integer> {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        super(carRepository);
        this.carRepository = carRepository;
    }

    public Page<Car> searchCars(Specification<Car> spec, Pageable pageable) {
        return carRepository.findAll(spec, pageable);
    }

}