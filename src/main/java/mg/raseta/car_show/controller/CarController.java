package mg.raseta.car_show.controller;

import lombok.AllArgsConstructor;
import mg.raseta.car_show.model.Car;
import mg.raseta.car_show.service.implementations.CarService;
import mg.raseta.car_show.specification.GenericModelSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car_show/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;
    private final GenericModelSpecification<Car> genericModelSpecification;

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createCar = carService.save(car);
        return ResponseEntity.ok(createCar);
    }

    @GetMapping
    public Page<Car> searchCars(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer power,
            @RequestParam(required = false) Integer placeNumber,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer carTypeId,
            @RequestParam(required = false) Integer motorTypeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Specification<Car> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(genericModelSpecification.hasString(name, "name"));
        }
        if (model != null) {
            spec = spec.and(genericModelSpecification.hasString(model, "model"));
        }
        if (price != null) {
            spec = spec.and(genericModelSpecification.hasInteger(price, "price"));
        }
        if (color != null) {
            spec = spec.and(genericModelSpecification.hasString(color, "color"));
        }
        if (power != null) {
            spec = spec.and(genericModelSpecification.hasInteger(power, "power"));
        }
        if (placeNumber != null) {
            spec = spec.and(genericModelSpecification.hasInteger(placeNumber, "placeNumber"));
        }
        if (status != null) {
            spec = spec.and(genericModelSpecification.hasBoolean(status, "status"));
        }
        if (brandId != null) {
            spec = spec.and(genericModelSpecification.hasInteger(brandId, "brandId"));
        }
        if (carTypeId != null) {
            spec = spec.and(genericModelSpecification.hasInteger(carTypeId, "carTypeId"));
        }
        if (motorTypeId != null) {
            spec = spec.and(genericModelSpecification.hasInteger(motorTypeId, "motorTypeId"));
        }

        Pageable pageable = PageRequest.of(page, size);
        return carService.searchCars(spec, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(
            @PathVariable int id,
            @RequestBody Car car
    ) {
        Car toUpdate = carService.update(id, car);
        return ResponseEntity.ok(toUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}