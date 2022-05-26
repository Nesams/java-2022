package ee.taltech.iti0202.tennis.building;

import ee.taltech.iti0202.tennis.club.Club;
import ee.taltech.iti0202.tennis.table.Table;

import java.util.ArrayList;

public class Building {
    private final ArrayList<Table> tables;
    private final Club club;

    public Building(Club club) {
        this.tables = new ArrayList<>();
        this.club = club;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public Club getClub() {
        return club;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }
}
