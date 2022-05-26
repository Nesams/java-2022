package ee.taltech.iti0202.tennis;

import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.club.Club;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;
import ee.taltech.iti0202.tennis.exceptions.TableAlreadyBookedException;
import ee.taltech.iti0202.tennis.exceptions.TrainingIsFullException;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Trainer;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class ClientsRankingTests {

    @Test
    void testRankingClubsClientsEasy()
            throws TrainingIsFullException, TableAlreadyBookedException, ParseException,
            FalseAgeException, FalseMeasurementsException {
        Club testClub = new Club();

        Building testBuilding1 = new Building(testClub);
        Building testBuilding2 = new Building(testClub);
        Building testBuilding3 = new Building(testClub);

        Table testTable1 = new Table(14, 15, 2, testBuilding1);
        Table testTable2 = new Table(11, 12, 3, testBuilding1);
        Table testTable5 = new Table(3, 3, 3, testBuilding3);


        Table testTable3 = new Table(9, 10, 4, testBuilding2);
        Table testTable4 = new Table(9, 10, 4, testBuilding2);

        Trainer testTrainerOtt =
                new Trainer("Ott", "Kiivikas", 50, "ott.kiivikas@gmail.com");
        Trainer testTrainerJaanika =
                new Trainer("Jaanika", "Paju", 35, "jaanika@gmail.com");

        Client testClientMati = new Client("Mati", "Tamm", 15, "Mati@gmail.com");
        Client testClientKati = new Client("Kati", "Okas", 30, "kati.kati@gmail.com");
        Client clientMihkel = new Client("Mihkel", "Okas", 23, "mihkel@gmail.com");
        Client clientUku = new Client("Uku", "Poom", 12, "uku@gmail.com");
        Client clientKelli = new Client("Kelli", "Suva", 35, "Kelli@gmail.com");
        Client clientTaavi = new Client("Taavi", "Tare", 49, "taavi@hotmail.com");

        Training testTraining1 = testTrainerOtt
                .createATraining("2022/04/20 11:11", "2022/04/20 12:12", 5, testBuilding1).get();
        Training testTraining2 = testTrainerJaanika
                .createATraining("2022/04/20 11:11", "2022/04/20 12:12", 7, testBuilding1).get();
        Training testTraining3 = testTrainerJaanika
                .createATraining("2021/11/11 12:12", "2021/11/11 14:12", 8, testBuilding2).get();

        Training recentTraining1 = testTrainerOtt
                .createATraining("2022/05/24 11:00", "2022/05/24 13:00", 5, testBuilding1).get();
        Training recentTraining2 = testTrainerJaanika
                .createATraining("2022/05/23 11:00", "2022/05/23 13:00", 6, testBuilding2).get();
        Training recentTraining3 = testTrainerOtt
                .createATraining("2022/05/25 11:00", "2022/05/25 13:00", 4, testBuilding1).get();

        List<Table> tables = List.of(testTable1, testTable2, testTable3);

        //Mati has 2 trainings and 3 bookings
        testClientMati.registerToTraining(testTraining1);
        testClientMati.registerToTraining(testTraining2);

        testClientMati.bookATable("2022/03/20 11:11", "2022/03/20 12:12", testTable1);
        testClientMati.bookATable("2022/03/20 11:11", "2022/03/20 12:12", testTable2);
        testClientMati.bookATable("2022/04/20 20:11", "2022/04/20 21:12", testTable3);

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
        clientMihkel.bookATable("2022/01/20 11:11", "2022/01/20 12:12", testTable4);
        clientMihkel.bookATable("2022/02/02 12:12", "2022/02/02 12:30", testTable2);

        //easy different trainings + bookings
        Assertions.assertEquals(testClub.sortClientsByActivityLevel(),
                List.of(clientMihkel, testClientMati, clientTaavi, testClientKati));


        testClientMati.bookATable("2022/04/20 20:11", "2022/04/20 21:12", testTable5);

        //Trainings + Bookings same, trainings different
        Assertions.assertEquals(testClub.sortClientsByActivityLevel(),
                List.of(clientMihkel, testClientMati, clientTaavi, testClientKati));

        testClientMati.registerToTraining(testTraining3);

        //Trainings + bookings same, trainings same, visited buildings different
        Assertions.assertEquals(testClub.sortClientsByActivityLevel(),
                List.of(testClientMati, clientMihkel, clientTaavi, testClientKati));

        clientMihkel.bookATable("2022/01/25 11:11", "2022/01/25 12:12", testTable5);
        testClientMati.bookATable("2021/03/20 11:11", "2021/03/20 12:12", testTable1);

        //Trainings + bookings same, trainings same, visited buildings same, time spent on trainings and bookings same
        Assertions.assertEquals(testClub.sortClientsByActivityLevel(),
                List.of(testClientMati, clientMihkel, clientTaavi, testClientKati));

        //Testing sorting club clients based on n days data

        //Mati has 2 recent trainings and 3 recent bookings
        testClientMati.registerToTraining(recentTraining1);
        testClientMati.registerToTraining(recentTraining2);

        testClientMati.bookATable("2022/05/23 11:11", "2022/05/23 12:12", testTable1);
        testClientMati.bookATable("2022/05/24 11:11", "2022/05/24 12:12", testTable2);
        testClientMati.bookATable("2022/05/25 20:11", "2022/05/25 21:12", testTable3);

        //Kati has 1 recent training and 1 recent booking
        testClientKati.registerToTraining(recentTraining1);
        testClientKati.bookATable("2022/05/24 11:11", "2022/05/24 12:12", testTable1);

        //Taavi has 2 recent trainings and 2 recent bookings
        clientTaavi.registerToTraining(recentTraining3);
        clientTaavi.registerToTraining(recentTraining1);

        clientTaavi.bookATable("2022/05/23 11:11", "2022/05/23 12:12", testTable2);
        clientTaavi.bookATable("2022/05/22 18:11", "2022/05/22 19:12", testTable1);

        //Mati has the most recent trainings + bookings
        Assertions.assertEquals(testClub.sortClientsBasedOnLastNDaysData(5),
                List.of(testClientMati, clientTaavi, testClientKati, clientMihkel));

        clientTaavi.registerToTraining(recentTraining2);
        clientTaavi.bookATable("2022/05/25 10:11", "2022/05/25 10:30", testTable3);

        //Taavi and Mati have the same amount bookings + trainings, but Taavi has more trainings
        Assertions.assertEquals(testClub.sortClientsBasedOnLastNDaysData(5),
                List.of(clientTaavi, testClientMati, testClientKati, clientMihkel));

        testClientMati.registerToTraining(recentTraining3);

        //Taavi and MAti have the same amount trainings and bookings,
        //trainings, visited buildings, but time spent on trainings and bookings is different
        Assertions.assertEquals(testClub.sortClientsBasedOnLastNDaysData(5),
                List.of(testClientMati, clientTaavi, testClientKati, clientMihkel));

    }
}
