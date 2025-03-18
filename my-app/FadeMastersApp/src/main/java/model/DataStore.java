package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import model.Appointment;

public class DataStore {
    private final Map<String, User> users = new HashMap<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private static DataStore instance;

    private DataStore() {
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> getAppointmentsForUser(String userEmail) {
        return appointments.stream()
                .filter(appointment -> appointment.getUser().getEmail().equals(userEmail))
                .toList();
    }

    public void deleteAppointment(UUID appointmentId) {
        appointments.removeIf(appointment -> appointment.getId().equals(appointmentId));
    }
}