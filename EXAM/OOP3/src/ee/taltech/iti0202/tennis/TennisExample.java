package ee.taltech.iti0202.tennis;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.club.Club;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.exceptions.FalseMeasurementsException;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Trainer;
import ee.taltech.iti0202.tennis.table.Table;
import ee.taltech.iti0202.tennis.training.Training;

import java.text.ParseException;

public class TennisExample {
    public static void main(String[] args) throws FalseAgeException, FalseMeasurementsException, ParseException {
        Club club = new Club();
        Building building1 = new Building(club);
        Building building2 = new Building(club);

        club.addBuilding(building1);
        club.addBuilding(building2);

        Table table1 = new Table(15, 15, 2, building1);
        Table table2 = new Table(12, 12, 3, building1);


        Table table3 = new Table(10, 10, 4, building2);
        Table table4 = new Table(10, 10, 4, building2);

        Trainer trainerOtt = new Trainer("Ott", "Kiivikas", 50, "ott.kiivikas@gmail.com");
        Client clientMati = new Client("Mati", "Tamm", 15, "Mati@gmail.com");
        Client clientKati = new Client("Kati", "Okas", 30, "kati.kati@gmail.com");
        try {
            Client client = new Client("Robot", "Robert", -2, "haha@gmail.com");
        } catch (FalseAgeException f) {
            System.out.println(f.getReason()); //False age parameter
        }

        Training training1 = trainerOtt.createATraining("2022/05/27 11:11", "2022/05/27 12:12", 5, building1).get();

        Booking booking1 = clientMati.bookATable("2022/05/25 11:11", "2022/05/25 12:12", table1);

        clientKati.registerToTraining(training1);
        clientMati.registerToTraining(training1);

        System.out.println(club.getBuildings()); // [building1, building2]
        System.out.println(building1.getClub()); //[club]
        System.out.println(building1.getTables()); //[table1, table2]
        System.out.println(building2.getTables()); //[table3, table4]

        System.out.println(training1.getEnddate()); //[Fri May 27 12:12:00 EEST 2022]

        System.out.println(clientMati.getAllBookings()); //[booking1]
        System.out.println(clientMati.getTimeSpentOnBookings()); //[many milliseconds]

        System.out.println(training1.getParticipants()); //[kati, mati]



    }
}
