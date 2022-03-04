package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;

public interface Fixable {
    /**
     * @throws CannotFixException
     */
    void fix() throws CannotFixException;

    /**
     * @return int
     */
    int getTimesFixed();
}
