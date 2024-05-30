package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends GenericRepository<Brand, Integer> {}