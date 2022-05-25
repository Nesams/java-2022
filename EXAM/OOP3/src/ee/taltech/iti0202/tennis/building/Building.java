package ee.taltech.iti0202.tennis.building;

import ee.taltech.iti0202.tennis.table.Table;

import java.util.ArrayList;

public class Building {
    private final ArrayList<Table> tables;

    public Building() {
        this.tables = new ArrayList<>();
    }

    public ArrayList<Table> getTables() {
        return tables;
    }
}
