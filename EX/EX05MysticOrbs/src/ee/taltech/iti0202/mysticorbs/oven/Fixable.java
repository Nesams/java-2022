package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixExceptions;

public interface Fixable {
    /**
     * @throws CannotFixExceptions
     */
    void fix() throws CannotFixExceptions;

    /**
     * @return int
     */
    int getTimesFixed();
}
