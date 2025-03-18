package controller;

import java.util.List;
import java.util.UUID;
import service.BookingService;
import service.BookingServiceImpl;

public class BookingController {
    private final BookingService bookingService = new BookingServiceImpl();

    public void registerUser(String name, String email, String phoneNumber, boolean admin) {
        User user = new User(name, email, phoneNumber, 0, admin);
        bookingService.registerUser(user);
    }

    public User getUser(String email) {
        return bookingService.getUser(email);
    }

    public void bookAppointment(String userEmail, String barber, String bookedForStr, String timeStr) {
        bookingService.bookAppointment(userEmail, barber, bookedForStr, timeStr);
    }

    public List<Appointment> getAllAppointments() {
        return bookingService.getAllAppointments();
    }

    public List<Appointment> getAppointmentsForUser(String userEmail) {
        return bookingService.getAppointmentsForUser(userEmail);
    }

    public void deleteAppointment(UUID appointmentId) {
        bookingService.deleteAppointment(appointmentId);
    }
}