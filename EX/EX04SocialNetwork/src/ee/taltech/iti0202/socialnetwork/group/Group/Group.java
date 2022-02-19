package ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.*;

public class Group {
    private String name;
    private final User owner;
    private final HashSet<User> participants;
    private final LinkedList<Message> groupMessages;

    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.participants = new HashSet<>();
        this.groupMessages = new LinkedList<>();
        addUser(owner);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return this.owner;
    }

    public void addUser(User user) {
        participants.add(user);
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void publishMessage(Message message) {
        if (participants.contains(message.getAuthor())) {
            groupMessages.add(message);
        }
    }

    public List<Message> getMessages() {
        return groupMessages;
    }
}