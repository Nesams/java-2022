package ee.taltech.iti0202.socialnetwork.user;
public class User {

    private final String name;
    private final Integer age;

    public User(String name) {
        this.name = name;
        this.age = null;
    }
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;

    }
    public String getName() {
        return this.name;
    }
    public Integer getAge() {
        return this.age;
    }
}