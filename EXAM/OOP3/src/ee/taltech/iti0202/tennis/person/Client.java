package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends Person {

    private final ArrayList<Training> trainings;
    private final ArrayList<Booking> bookings;
    private final ArrayList<Building> buildings;

    public Client(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.CLIENT);
        this.trainings = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public boolean registerToTraining(Training training) {
        if (training.getMaxParticipants() > 0) {
            training.addParticipant(this);
            trainings.add(training);
            addBuilding(training.getBuilding());
            training.getBuilding().getClub().addClient(this);
            return true;
        }
        return false;
    }

    public boolean bookATable(String start, String end, Table table) throws ParseException {
        List<Table> tables = List.of(table);
        Booking booking = new Booking(start, end, tables);
        bookings.add(booking);
        table.addBooking(booking);
        addBuilding(table.getBuilding());
        return true;
    }

    public void addBuilding(Building building) {
        if (!buildings.contains(building)) {
            buildings.add(building);
        }
    }

    /**
     * Returns client's ongoing trainings.
     * @param time
     * @return
     */
    public List<Training> getOnGoingTrainings(Date time) {
        return trainings.stream().filter(training -> training.getEnddate().after(time)).toList();
    }

    /**
     * Returns client's ongoing bookings.
     * @param time
     * @return
     */
    public List<Booking> getOnGoingBookings(Date time) {
        return bookings.stream().filter(booking -> booking.getEndingTime().after(time)).toList();
    }

    /**
     * Returns client's previous trainings.
     * @param time
     * @return
     */
    public List<Training> getPreviousTrainings(Date time) {
        return trainings.stream().filter(training -> training.getEnddate().before(time)).toList();
    }

    /**
     * Returns client's previous bookings.
     * @param time
     * @return
     */
    public List<Booking> getPreviousBookings(Date time) {
        return bookings.stream().filter(booking -> booking.getEndingTime().before(time)).toList();
    }
    public ArrayList<Training> getAllTrainings(String time) {
        return trainings;
    }
    public ArrayList<Booking> getAllBookings(String time){
        return bookings;
    }
    public long getTimeSpentOnTrainings() {
        long timeSum = 0L;
        for (Training training: trainings) {
            timeSum += training.getEnddate().getTime() - training.getStartdate().getTime();
        }
        return timeSum;
    }
    public long getTimeSpentOnBookings() {
        long timeSum = 0L;
        for (Booking booking: bookings) {
            timeSum += booking.getEndingTime().getTime() - booking.getStartingTime().getTime();
        }
        return timeSum;
    }
}
