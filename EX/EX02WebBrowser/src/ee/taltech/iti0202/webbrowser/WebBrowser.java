package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class WebBrowser {
    private String homePAge = "google.com";
    public Stack<String> backStack = new Stack<>();
    public Stack<String> forwardStack = new Stack<>();
    public String currentPage = homePAge;
    public ArrayList<String> visitedPagesList = new ArrayList<>(List.of("google.com"));
    public HashMap<String, Integer> visitedPages = new HashMap<>();
    public ArrayList<String> bookmarks = new ArrayList<>();
    public static String visitFirst = "";
    public static String visitSecond = "";
    public static String visitThird = "";
    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homePAge);
    }
    /**
     * Goes back to previous page.
     */
    public void back() {
        if (!backStack.isEmpty()) {
            forwardStack.push(currentPage);
            currentPage = backStack.pop();
            goTo(currentPage);
            visitedPagesList.add(currentPage);
        }
    }
    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (!forwardStack.isEmpty()) {
            backStack.push(currentPage);
            currentPage = forwardStack.pop();
            goTo(currentPage);
            visitedPagesList.add(currentPage);
        }
    }
    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        if (!(Objects.equals(currentPage, url))) {
            backStack.push(currentPage);
            currentPage = url;
            forwardStack.clear();
            visitedPagesList.add(url);
        }
    }
    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarks.contains(currentPage)) {
            bookmarks.add(currentPage);
        }
    }
    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {

        bookmarks.remove(bookmark);
    }
    public List<String> getBookmarks() {

        return bookmarks;
    }
    public void setHomePage(String homePage) {

        homePAge = homePage;
    }
    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        for (String word : visitedPagesList) {
            visitedPages.put(word, visitedPages.getOrDefault(word, 0) + 1);
        }
        LinkedHashMap<String, Integer> sortedVisitedPages = new LinkedHashMap<>();
        visitedPages.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedVisitedPages.put(x.getKey(), x.getValue()));
        Map.Entry<String, Integer> entry = sortedVisitedPages.entrySet().iterator().next();
        if (sortedVisitedPages.size() >= 3) {
            Object secondKey = sortedVisitedPages.keySet().toArray()[1];
            int secondValue = sortedVisitedPages.get(secondKey);
            Object thirdKey = sortedVisitedPages.keySet().toArray()[2];
            int thirdValue = sortedVisitedPages.get(thirdKey);
            if (entry.getValue() == 1) {
                visitFirst = " visit";
            } else {
                visitFirst = " visits";
            }
            if (secondValue == 1) {
                visitSecond = " visit";
            } else {
                visitSecond = " visits";
            }
            if (thirdValue == 1) {
                visitThird = " visit";
            } else {
                visitThird = " visits";
            }
            String firstFromMap = entry.getKey() + " - " + entry.getValue() + visitFirst;
            String secondFromMap = secondKey + " - " + secondValue + visitSecond;
            String thirdFromMap = thirdKey + " - " + thirdValue + visitThird;
            String topVisited = String.format("%s%n%s%n%s", firstFromMap, secondFromMap, thirdFromMap);
            return topVisited;
        }
        if (sortedVisitedPages.size() == 2) {
            Object secondKey = sortedVisitedPages.keySet().toArray()[1];
            int secondValue = sortedVisitedPages.get(secondKey);
            if (entry.getValue().equals(1)) {
                visitFirst = " visit";
            } else {
                visitFirst = " visits";
            }
            if (secondValue == 1) {
                visitSecond = " visit";
            } else {
                visitSecond = " visits";
            }
            String firstFromMap = entry.getKey() + " - " + entry.getValue().toString() + visitFirst;
            String secondFromMap = secondKey + " - " + secondValue + visitSecond;
            String topVisited = String.format("%s%n%s", firstFromMap, secondFromMap);
            return topVisited;
        } else {
            if (entry.getValue().equals(1)) {
                visitFirst = " visit";
            } else {
                visitFirst = " visits";
            }
            String firstFromMap = entry.getKey() + " - " + entry.getValue().toString() + visitFirst;
            String topVisited = String.format("%s", firstFromMap);
            return topVisited;
        }
    }
    /**
    * Returns a list of all visited pages.
    *
    * Not to be confused with pages in your back-history.
    *
    * For example, if you visit "facebook.com" and hit back(),
    * then the whole history would be: ["google.com", "facebook.com", "google.com"]
    * @return list of all visited pages
    */
    public List<String> getHistory() {
        return visitedPagesList;
    }
    /**
    * Returns the active web page (string).
    * @return active web page
    */
    public String getCurrentUrl() {
        return currentPage;
    }
    /**
     * Returns the home page(string).
     * @return active web page
     */
    public String getHomePage() {
        return homePAge;
    }
    public static void main(String[] args) {
        WebBrowser webb = new WebBrowser();
        webb.goTo("twitter.com");
        webb.goTo("google.com");
        System.out.println(webb.visitedPagesList);
        System.out.println(webb.bookmarks);
        System.out.println(webb.getTop3VisitedPages());
    }
}
