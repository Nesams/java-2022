package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;

public interface Fixable {

    void fix() throws CannotFixException, CannotFixExceptions;

    int getTimesFixed();
}
