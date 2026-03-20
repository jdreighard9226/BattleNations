package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public class PartialDominationWorld extends AbstractWorld {

    public PartialDominationWorld(ArrayList<Region> regions) {
        super(regions);
    }

    public PartialDominationWorld() {
        super();
    }

    @Override
    public boolean isGameWon(List<Player> players) {
        return false;
    }
}
