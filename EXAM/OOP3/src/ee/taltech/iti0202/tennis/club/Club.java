package ee.taltech.iti0202.tennis.club;

import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Trainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Club {
    private final ArrayList<Building> buildings;
    private final ArrayList<Client> clients;
    private final ArrayList<Trainer> trainers;

    public Club() {
        this.buildings = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.trainers = new ArrayList<>();
    }
    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    public List<Client> sortClientsByActivityLevel() {
        clients.sort(Comparator.comparing(Client::getTrainingsAndBookingsSum)
                .thenComparingInt(Client::getTheNumberOfPreviousTrainings)
                .thenComparingInt(Client::getTheNumberOfVisitedBuildings)
                .thenComparingLong(Client::getTimeSpentOnTrainingsAndBookings)
                .thenComparingLong(Client::getTimeSpentOnTrainings)
                .thenComparing(Client::getSurname));
        return clients;
    }

    public List<Client> sortClientsBasedOnLastNDaysData(int n) {
        clients.sort(Comparator.comparing((Client c)-> c.getTheNumberOfNDaysTrainingsAndBookings(n))
                .thenComparingInt((Client c) -> c.getTheNumberOfLastNDaysOfTrainings(n))
                .thenComparingInt((Client c) -> c.getTheLastNDaysNumberOfVisitedBuildings(n))
                .thenComparingLong((Client c) -> c.getLastNDaysTimeSpentOnTrainingsAndBookings(n))
                .thenComparingLong((Client c) -> c.getLastNDaysTimeSpentOnTrainings(n))
                .thenComparing(Client::getSurname));
        return clients;
    }
}
