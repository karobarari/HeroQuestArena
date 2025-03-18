package service;

import java.util.List;
import java.util.UUID;
import model.Appointment;

public interface BookingService {
    void registerUser(User user);

    User getUser(String email);

    void bookAppointment(String userEmail, String barber, String bookedForStr, String timeStr);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsForUser(String userEmail);

    void deleteAppointment(UUID appointmentId);
}