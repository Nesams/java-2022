package ee.taltech.iti0202.tennis.person;

import ee.taltech.iti0202.tennis.exceptions.FalseAgeException;

import java.util.Optional;

public class Person {
    private final String firstname;
    private final String surname;
    private final int age;
    private final String email;
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
        }
        throw new FalseAgeException("False age parameter");
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }

    public Optional<Type> getType() {
        return type;
    }
}
