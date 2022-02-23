package ee.taltech.iti0202.tk1.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    private ArrayList<Painting> paintings;
    public Collector() {
        this.paintings = new ArrayList<>();
    }
    public boolean addPainting(Painting painting) {
        if (!paintings.contains(painting)) {
            paintings.add(painting);
            return true;
        }
        return false;
    }
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (this.paintings.contains(painting) && this != fellowCollector) {
            this.paintings.remove(painting);
            return true;
        }
        return false;
    }
    public List<Painting> getPaintings() {
        return this.paintings;
    }
}
