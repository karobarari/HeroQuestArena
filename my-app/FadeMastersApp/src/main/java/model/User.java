package model;

public class User {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private int bookings;
    private final boolean admin;

    public User(String name, String email, String phoneNumber, int bookings, boolean admin) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookings = bookings;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getBookings() {
        return bookings;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void incrementBookings() {
        this.bookings++;
    }

    public void decrementBookings() {
        this.bookings--;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bookings=" + bookings +
                ", admin=" + admin +
                '}';
    }
}