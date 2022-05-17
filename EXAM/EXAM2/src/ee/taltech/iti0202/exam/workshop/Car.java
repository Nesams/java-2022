package ee.taltech.iti0202.exam.workshop;
public class Car {
    private final String licencePlate;
    private int timesFixed;
    private boolean fixed;

    public Car(String licencePlate) {
        this.licencePlate = licencePlate;
        this.fixed = false;
        this.timesFixed = 0;
    }

    public int getTimesFixed() {
        return timesFixed;
    }

    public boolean isFixed() {
        return this.fixed;
    }
    public void setTimesFixed() {
        timesFixed++;
    }

    public String getLicencePlate() {
        return this.licencePlate;
    }
    public void setFixed(boolean bool) {
        this.fixed = bool;
    }
}