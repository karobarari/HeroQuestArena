package repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import model.Appointment;
import model.DataStore;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class BookingRepositoryImpl implements BookingRepository {
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public void saveUser(User user) {
        dataStore.getUsers().put(user.getEmail(), user);
    }

    @Override
    public User findUserByEmail(String email) {
        return dataStore.getUsers().get(email);
    }

    @Override
    public void bookAppointment(String userEmail, String barber, String bookedForStr, String timeStr) {
        User user = dataStore.getUsers().get(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        // Parse the bookedFor and time
        LocalDateTime bookedFor;
        LocalDateTime time;

        try {
            bookedFor = LocalDateTime.parse(bookedForStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            time = LocalDateTime.parse(bookedFor.toLocalDate() + "T" + timeStr + ":00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date or time format.  Use ISO_OFFSET_DATE_TIME for bookedFor (e.g., 2024-04-26T10:00:00.000Z) and HH:mm for time (e.g., 10:00)");
        }


        // Check for time slot availability
        if (!isTimeSlotAvailable(barber, bookedFor, timeStr)) {
            throw new IllegalArgumentException("Time slot is already booked.");
        }

        Appointment appointment = new Appointment(user, barber, LocalDateTime.now(), time, bookedFor);
        dataStore.getAppointments().add(appointment);
        user.incrementBookings();
        System.out.println("Appointment booked successfully: " + appointment);
    }

    private boolean isTimeSlotAvailable(String barber, LocalDateTime bookedFor, String timeStr) {
        return dataStore.getAppointments().stream().noneMatch(appointment ->
                appointment.getBarber().equals(barber) &&
                        appointment.getBookedFor().toLocalDate().equals(bookedFor.toLocalDate()) &&
                        appointment.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")).equals(timeStr));
    }

    @Override
    public List<Appointment> findAllAppointments() {
        return dataStore.getAllAppointments();
    }

    @Override
    public List<Appointment> findAppointmentsByUserEmail(String userEmail) {
        return dataStore.getAppointmentsForUser(userEmail);
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        dataStore.deleteAppointment(appointmentId);
    }
}