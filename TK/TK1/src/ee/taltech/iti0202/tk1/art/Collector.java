package ee.taltech.iti0202.tk1.art;

import java.util.ArrayList;
import java.util.List;

public class Collector {
    private ArrayList<Painting> paintings;

    /**
     * Collector object.
     */
    public Collector() {
        this.paintings = new ArrayList<>();
    }

    /**
     * @param painting
     * @return
     */
    public boolean addPainting(Painting painting) {
        if (!paintings.contains(painting)) {
            paintings.add(painting);
            return true;
        }
        return false;
    }

    /**
     * @param painting
     * @param fellowCollector
     * @return
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (this.paintings.contains(painting) && this != fellowCollector) {
            this.paintings.remove(painting);
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    public List<Painting> getPaintings() {
        return this.paintings;
    }
}
