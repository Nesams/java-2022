package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.timeConverting.TimeConverting;
import ee.taltech.iti0202.tennis.training.Training;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Trainer extends Person{
    private final ArrayList<Training> ongoingTrainings;
    private ArrayList<Training> previousTrainings;

    public Trainer(String firstname, String surname, int age, String email) throws FalseAgeException {
        super(firstname, surname, age, email);
        this.type = java.util.Optional.of(Type.TRAINER);
        this.ongoingTrainings = new ArrayList<>();
        this.previousTrainings = new ArrayList<>();
    }

    public Optional<Training> createATraining(String start, String end, int maxParticipants, Building building) {
        if (canCreateTraining(start, end)) {
            Training newTraining = new Training(start, end, maxParticipants, building);
            ongoingTrainings.add(newTraining);
            return Optional.of(newTraining);
        }
        return Optional.empty();
    }
    public Optional<Booking> addBookingToTraining(Training training, List<Table> tables) {
        List<Table> correctTables = tables.stream()
                .filter(table -> training.getBuilding().getTables().contains(table))
                .filter(table -> !table.isBooked(training.getStart(), training.getEnd())).toList();
        if (!correctTables.isEmpty()) {
            Booking newBooking = new Booking(training.getStart(), training.getEnd(), correctTables);
            training.addBooking(newBooking);
            return Optional.of(newBooking);
        }
        return Optional.empty();
    }
    public Boolean canCreateTraining(String start, String end) {
        TimeConverting timeConverter = new TimeConverting();
        List<Integer> timePeriod = timeConverter.getTimePeriod(start, end);
        for (Training training: ongoingTrainings) {
            int trainingStart = timeConverter.convertTimeStringToList(training.getStart()).get(0);
            int trainingEnd = timeConverter.convertTimeStringToList(training.getEnd()).get(0);
            if((timePeriod.contains(trainingStart) && !timePeriod.contains(trainingEnd))
                    || (!timePeriod.contains(trainingStart) && timePeriod.contains(trainingEnd)) ) {
                return false;
            }
        }
        return true;
    }
}