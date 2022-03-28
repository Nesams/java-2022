package ee.taltech.iti0202.kt.mail;
public class Letter {

    private String recipient;
    private String destinationCity;
    private String address;

    /**
     * Create a new letter with recipient, destination city and address.
     */
    public Letter(String recipient, String destinationCity, String address) {
        this.recipient = recipient;
        this.destinationCity = destinationCity;
        this.address = address;
    }
    public String getDestinationCity() {

        return this.destinationCity;
    }
    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRecipient() {
        return this.recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    /**
     * String representation of the letter.
     * The format is: City: %s, Address: %s, Recipient: %s
     */
    public String toString() {
        return "City: %s, Address: %s, Recipient: %s".formatted(this.destinationCity, this.address, this.recipient);
    }
}
