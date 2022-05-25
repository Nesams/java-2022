package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.timeConverting.TimeConverting;
import ee.taltech.iti0202.tennis.training.Training;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

    private final ArrayList<Training> trainings;
    private final ArrayList<Booking> bookings;

    public Client(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.CLIENT);
        this.trainings = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public boolean registerToTraining(Training training) {
        if (training.getMaxParticipants() > 0) {
            training.addParticipant(this);
            trainings.add(training);
            return true;
        }
        return false;
    }

    public boolean bookATable(String start, String end, Table table) {
        List<Table> tables = List.of(table);
        Booking booking = new Booking(start, end, tables);
        bookings.add(booking);
        return true;
    }

    /**
     * Returns client's ongoing trainings.
     * @param time
     * @return
     */
    public ArrayList<Training> getOnGoingTrainings(String time) {
        ArrayList<Training> ongoingTrainings = new ArrayList<>();
        TimeConverting timeConverter = new TimeConverting();
        int currentHours = timeConverter.convertTimeStringToList(time).get(0);
        int currentMin = timeConverter.convertTimeStringToList(time).get(1);
        for (Training t: trainings) {
            int trainingEndHour = timeConverter.convertTimeStringToList(t.getEnd()).get(0);
            int trainingEndMin = timeConverter.convertTimeStringToList(t.getEnd()).get(1);
            if (trainingEndHour > currentHours) {
                ongoingTrainings.add(t);
            }
            if (trainingEndHour == currentHours && trainingEndMin > currentMin) {
                ongoingTrainings.add(t);
            }
        }
        return ongoingTrainings;
    }

    /**
     * Returns client's ongoing bookings.
     * @param time
     * @return
     */
    public ArrayList<Booking> getOnGoingBookings(String time) {
        ArrayList<Booking> ongoingBookings = new ArrayList<>();
        TimeConverting timeConverter = new TimeConverting();
        int currentHours = timeConverter.convertTimeStringToList(time).get(0);
        int currentMin = timeConverter.convertTimeStringToList(time).get(1);
        for (Booking b: bookings) {
            int bookingEndHour = timeConverter.convertTimeStringToList(b.getEndingTime()).get(0);
            int bookingEndMin = timeConverter.convertTimeStringToList(b.getEndingTime()).get(1);
            if (bookingEndHour > currentHours) {
                ongoingBookings.add(b);
            }
            if (bookingEndHour == currentHours && bookingEndMin > currentMin) {
                ongoingBookings.add(b);
            }
        }
        return ongoingBookings;
    }

    /**
     * Returns client's previous trainings.
     * @param time
     * @return
     */
    public ArrayList<Training> getPreviousTrainings(String time) {
        ArrayList<Training> previousTrainings = new ArrayList<>();
        TimeConverting timeConverter = new TimeConverting();
        int currentHours = timeConverter.convertTimeStringToList(time).get(0);
        int currentMin = timeConverter.convertTimeStringToList(time).get(1);
        for (Training t: trainings) {
            int trainingEndHour = timeConverter.convertTimeStringToList(t.getEnd()).get(0);
            int trainingEndMin = timeConverter.convertTimeStringToList(t.getEnd()).get(1);
            if (trainingEndHour < currentHours) {
                previousTrainings.add(t);
            }
            if (trainingEndHour == currentHours && trainingEndMin < currentMin) {
                previousTrainings.add(t);
            }
        }
        return previousTrainings;
    }

    /**
     * Returns client's previous bookings.
     * @param time
     * @return
     */
    public ArrayList<Booking> getPreviousBookings(String time) {
        ArrayList<Booking> previousBookings = new ArrayList<>();
        TimeConverting timeConverter = new TimeConverting();
        int currentHours = timeConverter.convertTimeStringToList(time).get(0);
        int currentMin = timeConverter.convertTimeStringToList(time).get(1);
        for (Booking b: bookings) {
            int bookingEndHour = timeConverter.convertTimeStringToList(b.getEndingTime()).get(0);
            int bookingEndMin = timeConverter.convertTimeStringToList(b.getEndingTime()).get(1);
            if (bookingEndHour < currentHours) {
                previousBookings.add(b);
            }
            if (bookingEndHour == currentHours && bookingEndMin < currentMin) {
                previousBookings.add(b);
            }
        }
        return previousBookings;
    }
    public ArrayList<Training> getAllTrainings(String time) {
        return trainings;
    }
    public ArrayList<Booking> getAllBookings(String time){
        return bookings;
    }
}
