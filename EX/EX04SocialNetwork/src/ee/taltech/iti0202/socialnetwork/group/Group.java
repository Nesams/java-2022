package ee.taltech.iti0202.socialnetwork.group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Group {
    private String name;
    private final User owner;
    private final HashSet<User> participants;
    private final LinkedList<Message> groupMessages;
    /**
     * Constructor.
     */
    public Group(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.participants = new HashSet<>();
        this.groupMessages = new LinkedList<>();
        addUser(owner);
    }
    /**
     * @return Group's name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * Set new name for the group.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return group's owner.
     */
    public User getOwner() {
        return this.owner;
    }
    /**
     * Add new user to the group.
     */
    public void addUser(User user) {
        participants.add(user);
    }
    /**
     * Get group members.
     */
    public Set<User> getParticipants() {
        return participants;
    }
    /**
     * Add message to the group-messages if author is group member.
     */
    public void publishMessage(Message message) {
        if (participants.contains(message.getAuthor())) {
            groupMessages.add(message);
        }
    }
    /**
     * Get group-messages.
     */
    public List<Message> getMessages() {
        return groupMessages;
    }
}
