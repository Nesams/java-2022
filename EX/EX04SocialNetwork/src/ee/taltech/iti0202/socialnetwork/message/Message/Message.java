package ee.taltech.iti0202.socialnetwork.message.Message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private final String title;
    private final String message;
    private final User author;
    public Message(String title, String message, User author) {
        this.title = title;
        this.message = message;
        this.author = author;
    }
    public String getTitle() {
        return this.title;
    }
    public String getMessage() {
        return this.message;
    }
    public User getAuthor() {
        return this.author;
    }
}
