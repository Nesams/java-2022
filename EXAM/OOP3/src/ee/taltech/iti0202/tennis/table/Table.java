package ee.taltech.iti0202.tennis.table;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;
import ee.taltech.iti0202.tennis.timeConverting.TimeConverting;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final int length;
    private final int width;
    private final int netHeight;
    private final ArrayList<Booking> bookings;

    public Table(int length, int width, int netHeight) throws FalseMeasurementsException {
        if (length > 0 || width > 0) {
            this.length = length;
            this.width = width;
            this.netHeight = netHeight;
            this.bookings = new ArrayList<>();
        } else {
            throw new FalseMeasurementsException("False measurements");
        }
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public boolean isBooked(String start, String end) {
        TimeConverting timeConverter = new TimeConverting();
        List<Integer> timePeriod = timeConverter.getTimePeriod(start, end);
        for (Booking booking:bookings) {
            int bookingStart = timeConverter.convertTimeStringToList(booking.getStartingTime()).get(0);
            int bookingEnd = timeConverter.convertTimeStringToList(booking.getEndingTime()).get(0);
            if ((timePeriod.contains(bookingStart) && !timePeriod.contains(bookingEnd))
                    || (!timePeriod.contains(bookingStart) && timePeriod.contains(bookingEnd)) ) {
                return true;
            }
        }
        return false;
    }
}
