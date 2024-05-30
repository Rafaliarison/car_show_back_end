package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.CarTypes;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypesRepository extends GenericRepository<CarTypes, Integer> {}