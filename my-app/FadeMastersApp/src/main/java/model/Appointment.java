package model;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.core.userdetails.User;

public class Appointment {
    private final UUID id;
    private final User user;
    private final String barber;
    private final LocalDateTime createdAt;
    private final LocalDateTime time;
    private final LocalDateTime bookedFor;

    public Appointment(User user, String barber, LocalDateTime createdAt, LocalDateTime time, LocalDateTime bookedFor) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.barber = barber;
        this.createdAt = createdAt;
        this.time = time;
        this.bookedFor = bookedFor;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getBarber() {
        return barber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LocalDateTime getBookedFor() {
        return bookedFor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", user=" + user +
                ", barber='" + barber + '\'' +
                ", createdAt=" + createdAt +
                ", time=" + time +
                ", bookedFor=" + bookedFor +
                '}';
    }
}