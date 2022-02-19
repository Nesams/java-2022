package ee.taltech.iti0202.socialnetwork;

import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;

class SocialNetworkTest {
    final int nineteen = 19;
    final int twelve = 12;
    final int fourteen = 14;
    LinkedHashSet<Message> messagesSet = new LinkedHashSet<>();
    LinkedHashSet<Group> groups = new LinkedHashSet<>();
    User u1 = new User("Nele", nineteen);
    User u2 = new User("Old Guy");
    User u3 = new User("u3", twelve);
    User u4 = new User("u4", fourteen);
    Group g1 = new Group("Group1", u1);
    Message m1 = new Message("Hello", "World!", u2);
    Message m2 = new Message("Hi", "Guys!", u4);
    Feed f1 = new Feed(u2, messagesSet);
    SocialNetwork n1 = new SocialNetwork();
    @Test
    void testConstructors() {
        Assertions.assertEquals(u1.getName(), "Nele");
        Assertions.assertNull(u2.getAge());
        Assertions.assertEquals(u1.getAge(), nineteen);
        Assertions.assertEquals(g1.getName(), "Group1");
        Assertions.assertEquals(g1.getOwner(), u1);
        Assertions.assertEquals(m1.getAuthor(), u2);
        Assertions.assertEquals(m1.getTitle(), "Hello");
        Assertions.assertEquals(m1.getMessage(), "World!");
    }
    @Test
    void testIfSettingGroupNameWorks() {
        g1.setName("IAIB");
        Assertions.assertEquals(g1.getName(), "IAIB");
    }
    @Test
    void testIfAddingGroupMembersWorks() {
        g1.addUser(u2);
        g1.addUser(u3);
        g1.addUser(u4);
        Assertions.assertEquals(g1.getParticipants().size(), 4);
    }
    @Test
    void testIfPublishingMessagesWorks() {
        LinkedList<Message> groupMessages = new LinkedList<>();
        groupMessages.add(m1);
        g1.addUser(u2);
        g1.publishMessage(m1);
        Assertions.assertEquals(g1.getMessages(), groupMessages);
        g1.publishMessage(m2);
        Assertions.assertEquals(g1.getMessages(), groupMessages);
    }
    @Test
    void TestIfFeedConstructorWorks() {
        messagesSet.add(m1);
        Assertions.assertEquals(f1.getMessages(), messagesSet);
        Assertions.assertEquals(f1.getUser(), u2);
    }
    @Test
    void testIfAddingGroupsToSocialNetworkWorks() {
        n1.registerGroup(g1);
        groups.add(g1);
        Assertions.assertEquals(n1.getGroups(), groups);
    }
}