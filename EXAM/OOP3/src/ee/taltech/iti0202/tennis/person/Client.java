package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Client extends Person {

    private static ArrayList<Training> trainings;
    private static ArrayList<Booking> bookings;
    private final ArrayList<Building> buildings;
    private final SimpleDateFormat formatter;

    public Client(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.CLIENT);
        trainings = new ArrayList<>();
        bookings = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.formatter = new SimpleDateFormat("yyy/MM/dd HH:mm");
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

    @Override
    public String getSurname() {
        return super.getSurname();
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
    public int getTheNumberOfVisitedBuildings() {
        Date date = new Date();
        List<Building> visitedBuildings = new ArrayList<>();
        for (Training training: getPreviousTrainings(date)) {
            if (!visitedBuildings.contains(training.getBuilding())) {
                visitedBuildings.add(training.getBuilding());
            }
        }
        for (Booking booking:getPreviousBookings(date)) {
            if (!visitedBuildings.contains(booking.getTables().get(0).getBuilding())) {
                visitedBuildings.add(booking.getTables().get(0).getBuilding());
            }
        }
        return visitedBuildings.size();
    }

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
    public static List<Training> getPreviousTrainings(Date time) {
        return trainings.stream().filter(training -> training.getEnddate().before(time)).toList();
    }

    /**
     * Returns client's previous bookings.
     * @param time
     * @return
     */
    public static List<Booking> getPreviousBookings(Date time) {
        return bookings.stream().filter(booking -> booking.getEndingTime().before(time)).toList();
    }
    public static List<Training> getLastNDaysTrainings(int n) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date nDate = cal.getTime();
        return trainings.stream().filter(training -> training.getStartdate().after(nDate))
                .filter(training -> training.getEnddate().before(now)).toList();
    }
    public static List<Booking> getLastNDaysBooking(int n) {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -n);
        Date nDate = cal.getTime();
        return bookings.stream().filter(booking -> booking.getStartingTime().after(nDate))
                .filter(booking -> booking.getEndingTime().before(now)).toList();
    }
    public ArrayList<Training> getAllTrainings() {
        return trainings;
    }
    public ArrayList<Booking> getAllBookings(){
        return bookings;
    }
    public int getTheNumberOfPreviousTrainings() {
        Date date = new Date();
        return getPreviousTrainings(date).size();
    }
    public static int getTheNumberOfPreviousBookings() {
        Date date = new Date();
        return getPreviousBookings(date).size();
    }
    public int getTrainingsAndBookingsSum() {
        return getTheNumberOfPreviousTrainings() + getTheNumberOfPreviousBookings();
    }
    public long getTimeSpentOnTrainings() {
        Date date = new Date();
        long timeSum = 0L;
        for (Training training: getPreviousTrainings(date)) {
            timeSum += training.getEnddate().getTime() - training.getStartdate().getTime();
        }
        return timeSum;
    }
    public long getTimeSpentOnBookings() {
        Date date = new Date();
        long timeSum = 0L;
        for (Booking booking: getPreviousBookings(date)) {
            timeSum += booking.getEndingTime().getTime() - booking.getStartingTime().getTime();
        }
        return timeSum;
    }

    public long getTimeSpentOnTrainingsAndBookings() {
        return getTimeSpentOnTrainings() + getTimeSpentOnBookings();
    }

    public int getTheNumberOfLastNDaysOfTrainings(int n) {
        return getLastNDaysTrainings(n).size();
    }
    public int getTheNumberOfLastNDaysOfBookings(int n) {
        return getLastNDaysBooking(n).size();
    }
    public int getTheNumberOfNDaysTrainingsAndBookings(int n) {
        return getTheNumberOfLastNDaysOfTrainings(n) + getTheNumberOfLastNDaysOfBookings(n);
    }
    public long getLastNDaysTimeSpentOnTrainings(int n) {
        long timeSum = 0L;
        for (Training training: getLastNDaysTrainings(n)) {
            timeSum += training.getEnddate().getTime() - training.getStartdate().getTime();
        }
        return timeSum;
    }
    public long getLastNDaysTimeSpentOnBookings(int n) {
        long timeSum = 0L;
        for (Booking booking: getLastNDaysBooking(n)) {
            timeSum += booking.getEndingTime().getTime() - booking.getStartingTime().getTime();
        }
        return timeSum;
    }
    public long getLastNDaysTimeSpentOnTrainingsAndBookings(int n) {
        return getLastNDaysTimeSpentOnTrainings(n) + getLastNDaysTimeSpentOnBookings(n);
    }
}
