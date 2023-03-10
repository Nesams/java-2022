package ee.taltech.iti0202.tennis.club;

import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.person.Client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Club {

    private final ArrayList<Building> buildings;
    private final ArrayList<Client> clients;

    /**
     * Constructor, club has buildings and clients.
     */
    public Club() {
        this.buildings = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void addBuilding(Building building) {
        if (!buildings.contains(building)) {
            buildings.add(building);
        }
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }

    /**
     * Returns a sorted list of clients, based on
     * 1. Trainings and bookings number
     * 2. trainings number
     * 3. visited buildings number
     * 4. time spent on trainings and bookings
     * 5. time spent on trainings
     * 6. and then by surname.
     * @return
     */
    public List<Client> sortClientsByActivityLevel() {
        Comparator<Client> comparator = Comparator.comparing(Client::getTrainingsAndBookingsSum)
                .thenComparing(Client::getTheNumberOfPreviousTrainings)
                .thenComparing(Client::getTheNumberOfVisitedBuildings)
                .thenComparing(Client::getTimeSpentOnTrainingsAndBookings)
                .thenComparing(Client::getTimeSpentOnTrainings).reversed()
                .thenComparing(Client::getSurname);
        return clients.stream().sorted(comparator).collect(Collectors.toList());
    }
    /**
     * Returns a sorted list of clients, based on last n days data
     * 1. Trainings and bookings number
     * 2. trainings number
     * 3. visited buildings number
     * 4. time spent on trainings and bookings
     * 5. time spent on trainings
     * 6. and then by surname.
     * @return
     */
    public List<Client> sortClientsBasedOnLastNDaysData(int n) {
        Comparator<Client> comparator = Comparator.comparing((Client c) -> c.getTheNumberOfNDaysTrainingsAndBookings(n))
                .thenComparingInt((Client c) -> c.getTheNumberOfLastNDaysOfTrainings(n))
                .thenComparingInt((Client c) -> c.getTheLastNDaysNumberOfVisitedBuildings(n))
                .thenComparingLong((Client c) -> c.getLastNDaysTimeSpentOnTrainingsAndBookings(n))
                .thenComparingLong((Client c) -> c.getLastNDaysTimeSpentOnTrainings(n)).reversed()
                .thenComparing(Client::getSurname);
        return clients.stream().sorted(comparator).collect(Collectors.toList());
    }
}
