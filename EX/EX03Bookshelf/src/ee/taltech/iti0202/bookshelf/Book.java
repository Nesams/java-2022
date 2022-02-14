package ee.taltech.iti0202.bookshelf;

public class Book {

    private int id;
    private Person owner;
    private String author;
    private int yearOfPublishing;
    private int price;
    private String title;
    private static int booksIdcount;

    public static int getAndIncrementNextId() {
        int followingId = booksIdcount;
        booksIdcount++;
        return followingId;
    }
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.owner = null;
        this.id = getAndIncrementNextId();
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYearOfPublishing() {
        return this.yearOfPublishing;
    }

    public Person getOwner() {
        return this.owner;
    }

    public int getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean buy(Person buyer) {
        if (buyer == null && this.owner != null) {
            this.owner.buyBook(this);
            this.owner = null;
            return false;
        }
        if (buyer == this.owner || buyer.getMoney() < this.price) {
            return false;
        }
        if (this.price <= buyer.getMoney() && !(this.owner == buyer)) {
            if (this.owner != null && this.owner.getBooks().contains(this)) {
                this.owner.sellBook(this);
            }
            buyer.buyBook(this);
            return true;
        } else {
            return false;
            }
        }
    }
