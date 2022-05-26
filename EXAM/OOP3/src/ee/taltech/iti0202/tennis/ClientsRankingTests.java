package ee.taltech.iti0202.tennis;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.club.Club;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;
import ee.taltech.iti0202.tennis.exceptions.TableAlreadyBookedException;
import ee.taltech.iti0202.tennis.exceptions.TrainingIsFull;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Trainer;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class ClientsRankingTests {
    Club testClub = new Club();

    Building testBuilding1 = new Building(testClub);
    Building testBuilding2 = new Building(testClub);

    Table testTable1 = new Table(15, 15, 2, testBuilding1);
    Table testTable2 = new Table(12, 12, 3, testBuilding1);


    Table testTable3 = new Table(10, 10, 4, testBuilding2);
    Table testTable4 = new Table(10, 10, 4, testBuilding2);

    Trainer testTrainerOtt = new Trainer("Ott", "Kiivikas", 50, "ott.kiivikas@gmail.com");
    Trainer testTrainerJaanika = new Trainer("Jaanika", "Paju", 35, "jaanika@gmail.com");

    Client testClientMati = new Client("Mati", "Tamm", 15, "Mati@gmail.com");
    Client testClientKati = new Client("Kati", "Okas", 30, "kati.kati@gmail.com");
    Client clientMihkel = new Client("Mihkel", "Okas", 23, "mihkel@gmail.com");
    Client clientUku = new Client("Uku", "Poom", 12, "uku@gmail.com");
    Client clientKelli = new Client("Kelli", "Suva", 35, "Kelli@gmail.com");
    Client clientTaavi = new Client("Taavi", "Tare", 49, "taavi@hotmail.com");

    Training testTraining1 = testTrainerOtt.createATraining("2022/05/27 11:11", "2022/05/27 12:12", 5, testBuilding1).get();
    Training testTraining2 = testTrainerJaanika.createATraining("2022/05/27 11:11", "2022/05/27 12:12", 7, testBuilding1).get();
    Training testTraining3 = testTrainerJaanika.createATraining("2021/11/11 12:12", "2021/11/11 14:12", 8, testBuilding2).get();

    List<Table> tables = List.of(testTable1, testTable2, testTable3);
    List<Table> jaanikaTables = List.of(testTable1, testTable3);

    Booking testBooking1 = testTrainerOtt.addBookingToTraining(testTraining1, tables).get();

    public ClientsRankingTests() throws FalseAgeException, FalseMeasurementsException, ParseException, TableAlreadyBookedException {
    }

    @Test
    void testRankingClubsClientsEasy() throws TrainingIsFull, TableAlreadyBookedException, ParseException {
        //Mati has 2 trainings and 3 bookings
        testClientMati.registerToTraining(testTraining1);
        testClientMati.registerToTraining(testTraining2);

        testClientMati.bookATable("2022/03/20 11:11", "2022/03/20 12:12", testTable1);
        testClientMati.bookATable("2022/03/20 11:11", "2022/03/20 12:12", testTable2);
        testClientMati.bookATable("2022/04/20 11:11", "2022/04/20 12:12", testTable1);

        //Kati has 1 training and 1 booking
        testClientKati.registerToTraining(testTraining2);
        testClientKati.bookATable("2022/01/20 11:11", "2022/01/20 12:12", testTable1);

        //Taavi has 2 trainings and 2 bookings
        clientTaavi.registerToTraining(testTraining1);
        clientTaavi.registerToTraining(testTraining2);

        clientTaavi.bookATable("2022/01/20 11:11", "2022/01/20 12:12", testTable2);
        clientTaavi.bookATable("2022/03/20 18:11", "2022/03/20 19:12", testTable1);

        //Mihkel has 3 trainings and 3 bookings
        clientMihkel.registerToTraining(testTraining1);
        clientMihkel.registerToTraining(testTraining2);
        clientMihkel.registerToTraining(testTraining3);

        clientMihkel.bookATable("2022/01/25 11:11", "2022/01/25 12:12", testTable3);
        clientMihkel.bookATable("2022/01/25 11:11", "2022/01/25 12:12", testTable4);
        clientMihkel.bookATable("2022/02/02 12:12", "2022/02/02 12:30", testTable2);

        //easy
        Assertions.assertEquals(testClub.sortClientsByActivityLevel(), List.of(clientMihkel, testClientMati, clientTaavi, testClientKati));
    }
}
