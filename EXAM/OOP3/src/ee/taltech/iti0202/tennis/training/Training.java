package ee.taltech.iti0202.tennis.training;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.person.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Training {
    private final String start;
    private final String end;
    private final ArrayList<Client> participants;
    private final Date startDate;
    private final Date endDate;
    private int maxParticipants;
    private final Building building;
    private final ArrayList<Booking> bookings;

    /**
     * Constructor for Training class.
     * @param start
     * @param end
     * @param maxParticipants
     * @param building
     * @throws ParseException
     */
    public Training(String start, String end, int maxParticipants, Building building) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        this.start = start;
        this.startDate = sdf.parse(start);
        this.end = end;
        this.endDate = sdf.parse(end);
        this.maxParticipants = maxParticipants;
        this.building = building;
        this.bookings = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public String getStart() {
        return start;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getEnd() {
        return end;
    }

    public Date getEndDate() {
        return endDate;
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
