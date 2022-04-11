package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class DummyStrategy implements Strategy {
    private ArrayList<Action> actions;

    public DummyStrategy(List<Action> actionss) {
        this.actions = new ArrayList<>();
        actions.addAll(actionss);
    }
    @Override
    public Action getAction() {
//        Action action = actions.get(0);
//        actions.remove(0);
//        return action;
        return actions.remove(0);
    }
}
