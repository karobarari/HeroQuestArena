package view;

import java.util.List;
import model.Appointment;

public class View {
    public void displayAllAppointments(List<Appointment> appointments) {
        appointments.forEach(System.out::println);
    }

    public void displayUserAppointments(List<Appointment> appointments) {
        appointments.forEach(System.out::println);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}