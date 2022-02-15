package ee.taltech.iti0202.bookshelf;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

class BookTest {
    final int year1 = 2002;
    final int year2 = 2003;
    final int year3 = 2004;
    final int year4 = 2005;
    final int price1 = 30;
    final int price2 = 35;
    final int price3 = 40;
    final int money1 = 100;
    final int money2 = 500;
    List<Book> ownedBooks = new ArrayList<>();
    Book b1 = Book.of("Title1", "Author1", year1, price1);
    Book b2 = Book.of("Title2", "Author2", year2, price2);
    Book b3 = Book.of("Title3", "Author3", year3, price3);
    Book b4 = Book.of("Title3", "Author3", year3, price2);
    Book b5 = Book.of("Title4", "Author3", year3, price1);
    Book b6 = Book.of("Title6", price1);
    Book b7 = Book.of("Title6", price2);
    Person p1 = new Person("Name1", money1);
    Person p2 = new Person("Name2", money2);

    @org.junit.jupiter.api.Test
    void testConstructor() {
        Assertions.assertEquals(b3.getAuthor(), "Author3");
        Assertions.assertEquals(b1.getTitle(), "Title1");
        Assertions.assertEquals(b2.getPrice(), price2);
        Assertions.assertEquals(b5.getYearOfPublishing(), year3);
        Assertions.assertEquals(p1.getName(), "Name1");
        Assertions.assertEquals(p1.getMoney(), money1);
    }

    @org.junit.jupiter.api.Test
    void getBooksByOwner() {
        p1.buyBook(b1);
        p1.buyBook(b3);
        ownedBooks.add(b1);
        ownedBooks.add(b3);
        Assertions.assertEquals(Book.getBooksByOwner(p1), ownedBooks);
    }

    @org.junit.jupiter.api.Test
    void removeBook() {
        p1.buyBook(b2);
        Assertions.assertTrue(Book.getBooksByOwner(p1).contains(b2));
        Assertions.assertTrue(Book.getBooksByAuthor("Author2").contains(b2));
        Book.removeBook(b2);
        Assertions.assertFalse(Book.getBooksByOwner(p1).contains(b2));
        Assertions.assertFalse(Book.getBooksByAuthor("Author2").contains(b2));

    }

    @org.junit.jupiter.api.Test
    void testIfBuyerNullAndOwnerNotWorks() {
        b1.buy(p2);
        b1.buy(null);
        int p2Money = p2.getMoney();
        Assertions.assertEquals(p2.getMoney(), p2Money);
    }

    @org.junit.jupiter.api.Test
    void testIfBuyBookReturnsRight() {
        Assertions.assertTrue(b2.buy(p2));
        b1.setOwner(p2);
        Assertions.assertFalse(b1.buy(p2));
    }

    @org.junit.jupiter.api.Test
    void testSettingOwner() {
        b2.setOwner(p1);
        Assertions.assertEquals(b2.getOwner(), p1);
    }

    @org.junit.jupiter.api.Test
    void getBooksByAuthor() {
        List<Book> authorList = new ArrayList<>();
        authorList.add(b2);
        Assertions.assertEquals(authorList, Book.getBooksByAuthor("Author2"));
    }
}
