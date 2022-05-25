package ee.taltech.iti0202.tennis.booking;

import ee.taltech.iti0202.tennis.table.Table;

import java.util.List;

public class Booking {
    private final String startingTime;
    private final String endingTime;
    private final List<Table> tables;

    public Booking(String start, String end, List<Table> tables) {
        this.startingTime = start;
        this.endingTime = end;
        this.tables = tables;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public List<Table> getTables() {
        return tables;
    }
}
