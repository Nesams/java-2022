package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Trainer extends Person{
    private final ArrayList<Training> trainings;

    public Trainer(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.TRAINER);
        this.trainings = new ArrayList<>();
    }

    public Optional<Training> createATraining(String start, String end, int maxParticipants, Building building) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm");
        if (canCreateTraining(sdf.parse(start), sdf.parse(end))) {
            Training newTraining = new Training(start, end, maxParticipants, building);
            trainings.add(newTraining);
            return Optional.of(newTraining);
        }
        return Optional.empty();
    }
    public Optional<Booking> addBookingToTraining(Training training, List<Table> tables) throws ParseException {
        List<Table> correctTables = tables.stream()
                .filter(table -> training.getBuilding().getTables().contains(table))
                .filter(table -> !table.isBooked(training.getStartdate(), training.getEnddate())).toList();
        if (!correctTables.isEmpty()) {
            Booking newBooking = new Booking(training.getStart(), training.getEnd(), correctTables);
            for (Table table: correctTables) {
                table.addBooking(newBooking);
            }
            training.addBooking(newBooking);
            return Optional.of(newBooking);
        }
        return Optional.empty();
    }
    public Boolean canCreateTraining(Date start, Date end) {
        for (Training training: trainings) {
            if (training.getStartdate().before(start) && training.getEnddate().after(end)) {
                return false;
            }
            if (training.getStartdate().after(start) && training.getEnddate().before(end)) {
                return false;
            }
            if (training.getStartdate().equals(start) || training.getEnddate().equals(end)) {
                return false;
            }
        }
        return true;
    }
}