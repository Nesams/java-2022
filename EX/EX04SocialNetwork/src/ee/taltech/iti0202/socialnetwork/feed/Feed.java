package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.Set;

public class Feed {
    private final User user;
    private final Set<Message> messages;
    /**
     * Constructor for the new feed.
     */
    public Feed(User user, Set<Message> messages) {
        this.user = user;
        this.messages = messages;
    }
    public User getUser() {
        return this.user;
    }
    public Set<Message> getMessages() {
        return this.messages;
    }
}
