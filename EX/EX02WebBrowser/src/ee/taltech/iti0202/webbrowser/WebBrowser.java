package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Comparator;

public class WebBrowser {
    private String homePAge = "google.com";
    public Stack<String> backStack = new Stack<>();
    public Stack<String> forwardStack = new Stack<>();
    public String currentPage = homePAge;
    public ArrayList<String> visitedPagesList = new ArrayList<>(List.of("google.com"));
    public HashMap<String, Integer> visitedPages = new HashMap<>();
    public ArrayList<String> bookmarks = new ArrayList<>();
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
        forwardStack.push(currentPage);
        goTo(backStack.pop());
    }
    /**
     * Goes forward to next page.
     */
    public void forward() {
        backStack.push(currentPage);
        goTo(forwardStack.pop());
    }
    /**
     * Go to a webpage.
     *
     * @param url url to go to
     */
    public void goTo(String url) {
        backStack.push(currentPage);
        currentPage = url;
        forwardStack.clear();
        visitedPagesList.add(url);
    }
    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {

        bookmarks.add(currentPage);
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
        LinkedHashMap <String, Integer> sortedVisitedPages = new LinkedHashMap<>();
        visitedPages.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedVisitedPages.put(x.getKey(), x.getValue()));
        Map.Entry<String, Integer> entry = sortedVisitedPages.entrySet().iterator().next();
        Object secondKey = sortedVisitedPages.keySet().toArray()[1];
        Object secondValue = sortedVisitedPages.get(secondKey);
        Object thirdKey = sortedVisitedPages.keySet().toArray()[2];
        Object thirdValue = sortedVisitedPages.get(thirdKey);
        String firstFromMap = entry.getKey() + " - " + entry.getValue().toString() + " visits";
        String secondFromMap = secondKey + " - " + secondValue + " visits";
        String thirdFromMap = thirdKey + " - " + thirdValue + " visits";
        String topVisited = String.format("%s%n%s%n%s", firstFromMap, secondFromMap, thirdFromMap);
        return topVisited;
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
    * 
    * @return active web page
    */
    public String getCurrentUrl() {
        return currentPage;
    }
    public static void main(String[] args) {
        WebBrowser webb = new WebBrowser();
        System.out.println(webb.getTop3VisitedPages());
        System.out.println(webb.visitedPagesList);
        System.out.println(webb.bookmarks);
    }
}
