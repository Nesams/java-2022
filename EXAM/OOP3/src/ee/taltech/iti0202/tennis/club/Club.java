package ee.taltech.iti0202.tennis.club;

import ee.taltech.iti0202.tennis.building.Building;
import ee.taltech.iti0202.tennis.person.Client;
import ee.taltech.iti0202.tennis.person.Trainer;

import java.util.ArrayList;

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

}
