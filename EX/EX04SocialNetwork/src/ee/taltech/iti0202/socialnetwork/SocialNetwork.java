package ee.taltech.iti0202.socialnetwork;

    import ee.taltech.iti0202.socialnetwork.feed.Feed;
    import ee.taltech.iti0202.socialnetwork.group.Group;
    import ee.taltech.iti0202.socialnetwork.message.Message;
    import ee.taltech.iti0202.socialnetwork.user.User;

    import java.util.LinkedHashSet;
    import java.util.LinkedList;
    import java.util.Set;
    import java.util.stream.Collectors;

public class SocialNetwork {
        private final LinkedHashSet<Group> groups = new LinkedHashSet<>();
        public void registerGroup(Group group) {
            groups.add(group);
        }

        public Set<Group> getGroups() {
            return this.groups;
        }

        public Feed getFeedForUser(User user) {
            LinkedList<Group> userGroups = (LinkedList<Group>) this.groups.stream()
                    .filter(u -> u.getParticipants().contains(user))
                    .collect(Collectors.toCollection(LinkedList::new));
            LinkedHashSet<Message> groupsMessages = new LinkedHashSet<>();
            for (Group g: userGroups) {
                groupsMessages.addAll(g.getMessages());
            }
            return new Feed(user, groupsMessages);
        }
    }