package ee.taltech.iti0202.socialnetwork.user;
public class User {

    private final String name;
    private final Integer age;
    /**
     * Constructor. Add new user with only name.
     */
    public User(String name) {
        this.name = name;
        this.age = null;
    }
    /**
     * Constructor. Add new user with name and age.
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    /**
     * @return user's name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return user's age.
     */
    public Integer getAge() {
        return this.age;
    }
}
