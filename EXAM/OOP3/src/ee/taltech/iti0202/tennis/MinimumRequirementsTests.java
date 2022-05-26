package ee.taltech.iti0202.tennis;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.club.Club;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;
import ee.taltech.iti0202.tennis.exceptions.TableAlreadyBookedException;
import ee.taltech.iti0202.tennis.exceptions.TrainingIsFull;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Person;
import ee.taltech.iti0202.tennis.person.Trainer;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class MinimumRequirementsTests {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    Club club = new Club();
    Club club2 = new Club();
    Club club3 = new Club();

    Building building1 = new Building(club);
    Building building2 = new Building(club);
    Building building3 = new Building(club2);
    Building building4 = new Building(club3);

    Table table1 = new Table(15, 16, 2, building1);
    Table table2 = new Table(12, 12, 3, building1);

    Table table3 = new Table(10, 10, 4, building2);
    Table table4 = new Table(10, 10, 4, building2);
    Table table5 = new Table(2, 2, 2, building2);

    Trainer trainerOtt = new Trainer("Ott", "Kiivikas", 50, "ott.kiivikas@gmail.com");
    Trainer trainerJaanika = new Trainer("Jaanika", "Paju", 35, "jaanika@gmail.com");

    Trainer trainerJoss = new Trainer("Joss", "Palk", 40, "Joss@gmail.com");

    Training training1 = trainerJoss
            .createATraining("2022/07/28 10:00", "2022/07/28 12:00", 1, building3).get();
    Training training2 = trainerJoss
            .createATraining("2022/05/28 10:00", "2022/05/28 12:00", 2, building3).get();
    Training training3 = trainerJoss
            .createATraining("2021/12/11 11:00", "2021/12/11 13:00", 4, building3).get();
    Training training4 = trainerJoss
            .createATraining("2021/12/11 15:00", "2021/12/11 18:00", 4, building4).get();

    Client clientToomas = new Client("Toomas", "Suvi", 40, "toomas@gmail.com");
    Client clientTriinu = new Client("Triinu", "Talv", 21, "Triinu11@gmail.com");


    public MinimumRequirementsTests() throws FalseMeasurementsException, FalseAgeException, ParseException {
    }

    @Test
    void testCreatingAClubAndAddingBuildings() {
        List<Building> buildings = List.of(building1, building2);

        Assertions.assertEquals(club.getBuildings().size(), 2);
        Assertions.assertEquals(club.getBuildings(), buildings);
        Assertions.assertEquals(club.getClients().size(), 0);
    }

    @Test
    void testAddTablesToBuildingsAndControlTheirMeasurements() {
        List<Table> tables = List.of(table1, table2);

        Assertions.assertEquals(building1.getTables(), tables);
        Assertions.assertEquals(building1.getTables().size(), 2);
        Assertions.assertEquals(building2.getTables().size(), 3);

        try {
            new Table(0, -2, 5, building1);
        } catch (FalseMeasurementsException e) {
            Assertions.assertEquals(e.getReason(), "False measurements");
        }

        try {
            new Table(3, 1, 0, building1);
        } catch (FalseMeasurementsException e) {
            Assertions.assertEquals(e.getReason(), "False measurements");
        }

        Assertions.assertEquals(building1.getTables(), tables);
    }

    @Test
    void testCreatingTrainerAndCreatingATraining() throws ParseException {
        Assertions.assertEquals(trainerOtt.getAge(), 50);
        Assertions.assertEquals(trainerJaanika.getType(), Optional.of(Person.Type.TRAINER));

        Training training1 = trainerOtt
                .createATraining("2022/05/27 11:11", "2022/05/27 12:12", 5, building1).get();
        Boolean falseBoolean = trainerOtt
                .canCreateTraining(sdf.parse("2022/05/27 11:11"), sdf.parse("2022/05/27 12:12"));

        Assertions.assertEquals(falseBoolean, false);
        Assertions.assertEquals(trainerOtt.getTrainings(), List.of(training1));
        Assertions.assertEquals(trainerJaanika.getTrainings().size(), 0);

        Assertions.assertEquals(training1.getEndDate(), sdf.parse("2022/05/27 12:12"));
        Assertions.assertEquals(training1.getBookings().size(), 0);
        Assertions.assertEquals(training1.getMaxParticipants(), 5);
        Assertions.assertEquals(training1.getParticipants().size(), 0);
    }

    @Test
    void testTrainerAddsTableBookingsToTraining() throws ParseException, TableAlreadyBookedException {
        Training training2 = trainerJaanika
                .createATraining("2022/05/28 11:11", "2022/05/28 12:12", 5, building2).get();

        List<Table> rightTables = List.of(table4, table5);
        List<Table> wrongTablesForTraining = List.of(table1, table2);
        List<Table> oneTableForTraining = List.of(table3);

        Booking booking1 = trainerJaanika.addBookingToTraining(training2, oneTableForTraining).get();
        Booking booking2 = trainerJaanika.addBookingToTraining(training2, rightTables).get();

        try {
            trainerJaanika.addBookingToTraining(training2, wrongTablesForTraining).get();
        } catch (TableAlreadyBookedException e) {
            Assertions.assertEquals(e.getReason(), "These tables are not available or at the wrong building");
        }

        Assertions.assertEquals(booking1.getTables().size(), 1);
        Assertions.assertEquals(training2.getBookings(), List.of(booking1, booking2));

        //Tables booking gets its start and end time from training time
        Assertions.assertEquals(table3.getBookings().get(0).getStartingTime(), sdf.parse("2022/05/28 11:11"));
        Assertions.assertEquals(table5.getBookings().get(0).getEndingTime(), sdf.parse("2022/05/28 12:12"));

    }

    @Test
    void testAddingClientsAndBookingTablesAndTrainings()
            throws TrainingIsFull, TableAlreadyBookedException, ParseException {
        Assertions.assertEquals(clientToomas.getSurname(), "Suvi");

        clientToomas.registerToTraining(training1);

        try {
            new Client("Vale", "Tundmatu", 0, "eitea@gmail.com");
        } catch (FalseAgeException e) {
            Assertions.assertEquals(e.getReason(), "False age parameter");
        }

        try {
            clientTriinu.registerToTraining(training1);
        } catch (TrainingIsFull e) {
            Assertions.assertEquals(e.getReason(), "The training is full");
        }

        //Client can book two tables to the same time
        Booking triinusBooking = clientTriinu
                .bookATable("2022/05/29 11:30", "2022/05/29 12:30", table1).get();
        Booking triinusSecondBooking = clientTriinu
                .bookATable("2022/05/29 11:30", "2022/05/29 12:30", table2).get();

        Assertions.assertEquals(clientToomas.getAllTrainings(), List.of(training1));
        Assertions.assertEquals(clientTriinu.getAllBookings(), List.of(triinusBooking, triinusSecondBooking));
    }

    @Test
    void testAskingClientsTrainingsAndBookingsLists()
            throws FalseAgeException, TableAlreadyBookedException, ParseException, TrainingIsFull {
        Client clientJuku = new Client("Juku", "Janes", 15, "Juku@gmail.com");

        Booking jukusB1 = clientJuku.bookATable("2021/02/15 11:00", "2021/02/15 12:00", table1).get();
        Booking jukusB2 = clientJuku.bookATable("2021/02/15 11:00", "2021/02/15 12:00", table2).get();
        Booking jukusB3 = clientJuku.bookATable("2023/02/15 11:00", "2023/02/15 12:00", table2).get();
        Booking jukusB4 = clientJuku.bookATable("2023/02/15 11:00", "2023/02/15 12:00", table4).get();

        clientJuku.registerToTraining(training2);
        clientJuku.registerToTraining(training3);

        Assertions.assertEquals(clientJuku.getAllBookings(), List.of(jukusB1, jukusB2, jukusB3, jukusB4));
        Assertions.assertEquals(clientJuku.getOnGoingBookings(), List.of(jukusB3, jukusB4));
        Assertions.assertEquals(clientJuku.getPreviousBookings(), List.of(jukusB1, jukusB2));

        Assertions.assertEquals(clientJuku.getAllTrainings(), List.of(training2, training3));
        Assertions.assertEquals(clientJuku.getOnGoingTrainings(), List.of(training2));
        Assertions.assertEquals(clientJuku.getPreviousTrainings(), List.of(training3));
    }

    @Test
    void testAskingTrainingParticipantsAndClubsClients() throws TrainingIsFull {
        clientTriinu.registerToTraining(training4);
        clientToomas.registerToTraining(training4);

        Assertions.assertEquals(training4.getParticipants(), List.of(clientTriinu, clientToomas));

        Assertions.assertEquals(club3.getClients(), List.of(clientTriinu, clientToomas));
    }

}
