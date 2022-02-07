package ee.taltech.iti0202.webbrowser;
import java.util.*;

public class WebBrowser {
    private String home_page = "Google.com";
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();
    private String currentPage = home_page;
    private ArrayList<String> visitedPagesList = new ArrayList<>();
    private HashMap<String, Integer> visitedPages = new HashMap<String, Integer>();
    private ArrayList<String> bookmarks = new ArrayList<>();
    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(home_page);
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
        visitedPages.put(url, visitedPages.getOrDefault(url, 0) + 1);
    }
    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark(String bookmark) {
        bookmarks.add(bookmark);
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
        home_page = homePage;
    }
    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        LinkedHashMap <String, Integer> sortedVisitedPages = new LinkedHashMap<>();
        visitedPages.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedVisitedPages.put(x.getKey(), x.getValue()));
        Map.Entry<String, Integer> entry = sortedVisitedPages.entrySet().iterator().next();
        String topVisited = String.format("%s%n%s", entry.getKey(), entry.getValue().toString());
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
}