package ee.taltech.iti0202.tennis.training;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.table.Table;

import java.util.ArrayList;
import java.util.Arrays;

public class Training {
    private final String start;
    private final String end;
    private final ArrayList<Client> participants;
    private int maxParticipants;
    private final Building building;
    private final ArrayList<Booking> bookings;

    public Training(String start, String end, int maxParticipants, Building building) {
        this.start = start;
        this.end = end;
        this.maxParticipants = maxParticipants;
        this.building = building;
        this.bookings = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void decreaseMaxParticipants() {
        this.maxParticipants--;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }
    public void addParticipant(Client client) {
        if (!participants.contains(client)) {
            participants.add(client);
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
}
