package repository;

import java.util.List;
import java.util.UUID;
import model.Appointment;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface BookingRepository {
    void saveUser(User user);

    User findUserByEmail(String email);

    void bookAppointment(String userEmail, String barber, String bookedForStr, String timeStr);

    List<Appointment> findAllAppointments();

    List<Appointment> findAppointmentsByUserEmail(String userEmail);

    void deleteAppointment(UUID appointmentId);
}