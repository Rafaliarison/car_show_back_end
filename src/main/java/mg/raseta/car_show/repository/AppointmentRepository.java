package mg.raseta.car_show.repository;

import mg.raseta.car_show.model.Appointment;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends GenericRepository<Appointment, Integer> {}