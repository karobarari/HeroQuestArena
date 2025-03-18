import controller.BookingController;
import java.util.List;
import java.util.UUID;
import view.View;

public class BarberShopBookingApp {
    public static void main(String[] args) {
        BookingController controller = new BookingController();
        View view = new View();

        // Register a user
        controller.registerUser("John Doe", "john.doe@example.com", "123-456-7890", false);

        // Book an appointment
        controller.bookAppointment("john.doe@example.com", "joe", "2024-04-27T10:00:00.000Z", "10:00");

        // Get all appointments
        List<Appointment> allAppointments = controller.getAllAppointments();
        view.displayAllAppointments(allAppointments);

        // Get user's appointments
        List<Appointment> userAppointments = controller.getAppointmentsForUser("john.doe@example.com");
        view.displayUserAppointments(userAppointments);

        // Delete an appointment
        if (!userAppointments.isEmpty()) {
            controller.deleteAppointment(userAppointments.get(0).getId());
            view.displayMessage("Appointment deleted successfully.");
        }

        // Show remaining appointments
        List<Appointment> remainingAppointments = controller.getAllAppointments();
        view.displayAllAppointments(remainingAppointments);
    }
}