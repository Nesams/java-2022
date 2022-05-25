package ee.taltech.iti0202.tennis.table;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;

import java.util.ArrayList;
import java.util.Date;

public class Table {
    private final int length;
    private final int width;
    private final int netHeight;
    private final ArrayList<Booking> bookings;
    private final Building building;

    public Table(int length, int width, int netHeight, Building building) throws FalseMeasurementsException {
        if (length > 0 || width > 0) {
            this.length = length;
            this.width = width;
            this.netHeight = netHeight;
            this.bookings = new ArrayList<>();
            this.building = building;
        } else {
            throw new FalseMeasurementsException("False measurements");
        }
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public boolean isBooked(Date start, Date end) {
        for (Booking booking: bookings) {
            //booked the whole time
            if (booking.getStartingTime().before(start) && booking.getEndingTime().after(end)) {
                return true;
            }
            //booking at the end
            if (booking.getStartingTime().after(start) && booking.getEndingTime().before(end)) {
                return true;
            }
            if (booking.getStartingTime().equals(start) || booking.getEndingTime().equals(end)) {
                return true;
            }
        }
        return false;
    }
}
