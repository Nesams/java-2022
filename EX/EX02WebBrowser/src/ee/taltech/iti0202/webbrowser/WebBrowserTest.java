package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WebBrowserTest {
    WebBrowser wb = new WebBrowser();
    public static final int FIFTY = 50;
    public static final int FIFTYONE = 51;

    @Test
    void testIfHomePageWorks() {
        wb.setHomePage("pinterest.com");
        Assertions.assertEquals("pinterest.com", wb.getHomePage());
    }

    @Test
    void testIfGoToWorks() {
        wb.goTo("facebook.com");
        wb.goTo("twitter.com");
        Assertions.assertEquals("twitter.com", wb.getCurrentUrl());
    }

    @Test
    void testIfGoingBackWorks() {
        wb.goTo("facebook.com");
        wb.goTo("pinterest.com");
        wb.goTo("instagram.com");
        wb.back();
        wb.back();
        Assertions.assertEquals("facebook.com", wb.getCurrentUrl());
    }
    @Test
    void testIfGoingForwardWorks() {
        wb.goTo("pinterest.com");
        wb.goTo("instagram.com");
        wb.back();
        wb.forward();
        Assertions.assertEquals("instagram.com", wb.getCurrentUrl());
    }

    @Test
    void testVisitedPagesListSize() {
        for (int i = 0; i < FIFTY; i++) {
            wb.goTo("page" + i);
        }
        Assertions.assertEquals(FIFTYONE, wb.getHistory().size());
    }
    @Test
    void testIfForwardStackIsEmpty() {
        wb.goTo("pg1");
        wb.goTo("pg2");
        wb.goTo("pg3");
        wb.goTo("pg4");
        wb.goTo("pg5");
        wb.back();
        wb.back();
        wb.back();
        wb.goTo("pg7");
        assertTrue(wb.forwardStack.isEmpty());
    }
    @Test
    void testIfDoestDoubleAddSamePageToListGoingBack() {
        wb.goTo("pg8");
        wb.goTo("pg8");
        wb.goTo("pg8");
        ArrayList<String> testList = new ArrayList<>(List.of("google.com", "pg8"));
        Assertions.assertEquals(testList, wb.getHistory());
    }
    @Test
    void getTop3VisitedPages() {
        wb.goTo("google.com");
        wb.goTo("twitter.com");
        wb.goTo("pg.com");
        wb.goTo("twitter.com");
        wb.goTo("pinterest.com");
        wb.goTo("google.com");
        wb.goTo("twitter.com");
        wb.goTo("pg.com");
        String testString = String.format("%s%n%s%n%s", "twitter.com - 3 visits", "google.com - 2 visits",
                "pg.com - 2 visits");
        Assertions.assertEquals(testString, wb.getTop3VisitedPages());
    }
    @Test
    void getTop3OnlyTwoPagesInHistory() {
        wb.goTo("pinterest.com");
        String testString = String.format("%s%n%s", "google.com - 1 visit", "pinterest.com - 1 visit");
    }
}
