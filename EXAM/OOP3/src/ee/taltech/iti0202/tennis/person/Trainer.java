package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.TableAlreadyBookedException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Trainer extends Person {
    private final ArrayList<Training> trainings;

    /**
     * Constructor for Trainer class.
     * @param firstname
     * @param surname
     * @param age
     * @param email
     * @throws FalseAgeException
     */
    public Trainer(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.TRAINER);
        this.trainings = new ArrayList<>();
    }

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    /**
     * Trainer can create a training if they don't have training at the same time.
     * @param start
     * @param end
     * @param maxParticipants
     * @param building
     * @return
     * @throws ParseException
     */
    public Optional<Training> createATraining(String start, String end, int maxParticipants, Building building)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        if (canCreateTraining(sdf.parse(start), sdf.parse(end))) {
            Training newTraining = new Training(start, end, maxParticipants, building);
            trainings.add(newTraining);
            return Optional.of(newTraining);
        }
        return Optional.empty();
    }

    /**
     * Trainer can book one or more tables for their training.
     * Tables must not be booked and must be in the right building.
     * @param training
     * @param tables
     * @return
     * @throws ParseException
     */
    public Optional<Booking> addBookingToTraining(Training training, List<Table> tables)
            throws ParseException, TableAlreadyBookedException {
        List<Table> correctTables = tables.stream()
                .filter(table -> training.getBuilding().getTables().contains(table))
                .filter(table -> !table.isBooked(training.getStartDate(), training.getEndDate())).toList();
        if (!correctTables.isEmpty()) {
            Booking newBooking = new Booking(training.getStart(), training.getEnd(), correctTables);
            for (Table table: correctTables) {
                table.addBooking(newBooking);
            }
            training.addBooking(newBooking);
            return Optional.of(newBooking);
        } else {
            throw new TableAlreadyBookedException("These tables are not available or at the wrong building");
        }
    }

    /**
     * Checking if trainer has training at the same time or not.
     * @param start
     * @param end
     * @return
     */
    public Boolean canCreateTraining(Date start, Date end) {
        for (Training training: trainings) {
            if (training.getStartDate().before(start) && training.getEndDate().after(end)) {
                return false;
            }
            if (training.getStartDate().after(start) && training.getEndDate().before(end)) {
                return false;
            }
            if (training.getStartDate().equals(start) || training.getEndDate().equals(end)) {
                return false;
            }
        }
        return true;
    }
}
