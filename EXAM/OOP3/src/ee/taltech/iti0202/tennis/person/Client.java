package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.TableAlreadyBookedException;
import ee.taltech.iti0202.tennis.exceptions.TrainingIsFull;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Client extends Person {

    private static List<Training> trainings;
    private static List<Booking> bookings;
    private final List<Building> buildings;
    private final SimpleDateFormat formatter;

    /**
     * Constructor for Client class.
     * @param firstname
     * @param surname
     * @param age should be positive nr
     * @param email
     * @throws FalseAgeException
     */
    public Client(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.CLIENT);
        trainings = new ArrayList<>();
        bookings = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.formatter = new SimpleDateFormat("yyy/MM/dd HH:mm");
    }
    public void addTraining(Training training) {
        if (!trainings.contains(training)) {
            trainings.add(training);
        }
    }

    /**
     * Client makes a registration for training, if there´s enough space they are registered.
     * Adds training to client´s trainings list.
     * Adds building to client´s buildings list.
     * Adds client to clubs clients list.
     * @param training
     * @return
     */
    public boolean registerToTraining(Training training) throws TrainingIsFull {
        if (training.getMaxParticipants() > 0) {
            training.addParticipant(this);
            addTraining(training);
            addBuilding(training.getBuilding());
            training.getBuilding().getClub().addClient(this);
            return true;
        } else {
            throw new TrainingIsFull("The training is full");
        }
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    /**
     * Client can book a 1 table if the table is not already booked.
     * @param start
     * @param end
     * @param table
     * @return Optiona.of(Booking)
     * @throws ParseException
     */
    public Optional<Booking> bookATable(String start, String end, Table table)
            throws ParseException, TableAlreadyBookedException {
        List<Table> tables = List.of(table);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (!table.isBooked(sdf.parse(start), sdf.parse(end))) {
            Booking booking = new Booking(start, end, tables);
            bookings.add(booking);
            table.addBooking(booking);
            addBuilding(table.getBuilding());
            return Optional.of(booking);
        } else {
            throw new TableAlreadyBookedException("Table is already booked");
        }
    }

    public void addBuilding(Building building) {
        if (!buildings.contains(building)) {
            buildings.add(building);
        }
    }

    /**
     * Returns the number of previously visited buildings.
     * @return
     */
    public int getTheNumberOfVisitedBuildings() {
        List<Building> visitedBuildings = new ArrayList<>();
        for (Training training: getPreviousTrainings()) {
            if (!visitedBuildings.contains(training.getBuilding())) {
                visitedBuildings.add(training.getBuilding());
            }
        }
        for (Booking booking:getPreviousBookings()) {
            if (!visitedBuildings.contains(booking.getTables().get(0).getBuilding())) {
                visitedBuildings.add(booking.getTables().get(0).getBuilding());
            }
        }
        return visitedBuildings.size();
    }

    /**
     * Returns the number of last n days worth of visited buildings.
     * @param n
     * @return
     */
    public int getTheLastNDaysNumberOfVisitedBuildings(int n) {
        List<Building> visitedBuildings = new ArrayList<>();
        for (Training training: getLastNDaysTrainings(n)) {
            if (!visitedBuildings.contains(training.getBuilding())) {
                visitedBuildings.add(training.getBuilding());
            }
        }
        for (Booking booking:getLastNDaysBooking(n)) {
            if (!visitedBuildings.contains(booking.getTables().get(0).getBuilding())) {
                visitedBuildings.add(booking.getTables().get(0).getBuilding());
            }
        }
        return visitedBuildings.size();
    }

    /**
     * Returns client's ongoing trainings.
     * @return
     */
    public List<Training> getOnGoingTrainings() {
        Date date = new Date();
        return trainings.stream().filter(training -> training.getEndDate().after(date)).toList();
    }

    /**
     * Returns client's ongoing bookings.
     * @return
     */
    public List<Booking> getOnGoingBookings() {
        Date date = new Date();
        return bookings.stream().filter(booking -> booking.getEndingTime().after(date)).toList();
    }

    /**
     * Returns client's previous trainings.
     * @return
     */
    public List<Training> getPreviousTrainings() {
        Date time = new Date();
        return trainings.stream().filter(training -> training.getEndDate().before(time)).toList();
    }

    /**
     * Returns client's previous bookings.
     * @return
     */
    public List<Booking> getPreviousBookings() {
        Date time = new Date();
        return bookings.stream().filter(booking -> booking.getEndingTime().before(time)).toList();
    }

    /**
     * Returns the list of last n days worth of trainings.
     * @param n
     * @return
     */
    public static List<Training> getLastNDaysTrainings(int n) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date nDate = cal.getTime();
        return trainings.stream().filter(training -> training.getStartDate().after(nDate))
                .filter(training -> training.getEndDate().before(now)).toList();
    }

    /**
     * Returns a list of last n days worth of bookings.
     * @param n
     * @return
     */
    public static List<Booking> getLastNDaysBooking(int n) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date nDate = cal.getTime();
        return bookings.stream().filter(booking -> booking.getStartingTime().after(nDate))
                .filter(booking -> booking.getEndingTime().before(now)).toList();
    }

    /**
     * Returns all the trainings.
     * @return
     */
    public List<Training> getAllTrainings() {
        return trainings;
    }

    /**
     * Returns all the bookings.
     * @return
     */
    public List<Booking> getAllBookings() {
        return bookings;
    }

    /**
     * Returns the number of completed trainings.
     * @return
     */
    public int getTheNumberOfPreviousTrainings() {
        return getPreviousTrainings().size();
    }

    /**
     * Returns the number of completed bookings.
     * @return
     */
    public int getTheNumberOfPreviousBookings() {
        return getPreviousBookings().size();
    }

    /**
     * Returns the sum of completed trainings and bookings.
     * @return
     */
    public int getTrainingsAndBookingsSum() {
        return getTheNumberOfPreviousTrainings() + getTheNumberOfPreviousBookings();
    }

    /**
     * Returns the amount of time spent on completed trainings.
     * @return
     */
    public long getTimeSpentOnTrainings() {
        long timeSum = 0L;
        for (Training training: getPreviousTrainings()) {
            timeSum += training.getEndDate().getTime() - training.getStartDate().getTime();
        }
        return timeSum;
    }

    /**
     * Returns the amount of time spent on completed bookings.
     * @return
     */
    public long getTimeSpentOnBookings() {
        long timeSum = 0L;
        for (Booking booking: getPreviousBookings()) {
            timeSum += booking.getEndingTime().getTime() - booking.getStartingTime().getTime();
        }
        return timeSum;
    }

    /**
     * Returns the amount of time spent on completed trainings and bookings.
     * @return
     */
    public long getTimeSpentOnTrainingsAndBookings() {
        return getTimeSpentOnTrainings() + getTimeSpentOnBookings();
    }

    /**
     * Returns the number of last n days trainings.
     * @param n
     * @return
     */
    public int getTheNumberOfLastNDaysOfTrainings(int n) {
        return getLastNDaysTrainings(n).size();
    }

    /**
     * Returns the number of last n days of bookings.
     * @param n
     * @return
     */
    public int getTheNumberOfLastNDaysOfBookings(int n) {
        return getLastNDaysBooking(n).size();
    }

    /**
     * Returns the last n days of trainings and bookings.
     * @param n
     * @return
     */
    public int getTheNumberOfNDaysTrainingsAndBookings(int n) {
        return getTheNumberOfLastNDaysOfTrainings(n) + getTheNumberOfLastNDaysOfBookings(n);
    }

    /**
     * Returns the amount of time spent on trainings in the last n days.
     * @param n
     * @return
     */
    public long getLastNDaysTimeSpentOnTrainings(int n) {
        long timeSum = 0L;
        for (Training training: getLastNDaysTrainings(n)) {
            timeSum += training.getEndDate().getTime() - training.getStartDate().getTime();
        }
        return timeSum;
    }

    /**
     * Returns the amount of time spent on bookings in the last n days.
     * @param n
     * @return
     */
    public long getLastNDaysTimeSpentOnBookings(int n) {
        long timeSum = 0L;
        for (Booking booking: getLastNDaysBooking(n)) {
            timeSum += booking.getEndingTime().getTime() - booking.getStartingTime().getTime();
        }
        return timeSum;
    }

    /**
     * Returns the amount of time spent on trainings and bookings in the last n days.
     * @param n
     * @return
     */
    public long getLastNDaysTimeSpentOnTrainingsAndBookings(int n) {
        return getLastNDaysTimeSpentOnTrainings(n) + getLastNDaysTimeSpentOnBookings(n);
    }
}
