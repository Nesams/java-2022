package ee.taltech.iti0202.tennis.booking;

import ee.taltech.iti0202.tennis.table.Table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Booking {
    private final Date startingTime;
    private final Date endingTime;
    private final List<Table> tables;

    /**
     * Constructor for Booking class.
     * @param start
     * @param end
     * @param tables
     * @throws ParseException
     */
    public Booking(String start, String end, List<Table> tables) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd HH:mm");
        this.startingTime = sdf.parse(start);
        this.endingTime = sdf.parse(end);
        this.tables = tables;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public List<Table> getTables() {
        return tables;
    }
}
