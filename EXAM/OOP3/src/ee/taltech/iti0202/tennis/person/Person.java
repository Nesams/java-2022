package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.booking.Booking;
import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;
import ee.taltech.iti0202.tennis.training.Training;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Person {
    private final String firstname;
    private final String surname;
    private final int age;
    private final String email;
    private final List<Training> trainings;
    private final List<Booking> bookings;
    Optional<Type> type;

    public enum Type {
        CLIENT, TRAINER
    }
    public Person(String firstname, String surname, int age, String email) throws FalseAgeException {
        if (age > 0) {
            this.firstname = firstname;
            this.surname = surname;
            this.age = age;
            this.email = email;
            this.type = Optional.empty();
            this.trainings = new ArrayList<>();
            this.bookings = new ArrayList<>();
        } else {
            throw new FalseAgeException("False age parameter");
        }
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Optional<Type> getType() {
        return type;
    }
}
