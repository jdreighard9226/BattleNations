package map;

import player.Player;

import java.util.ArrayList;
import java.util.List;

public class CapitalDominationWorld extends AbstractWorld {
    public CapitalDominationWorld(ArrayList<Region> regions) {
        super(regions);
    }

    @Override
    public boolean isGameWon(List<Player> players) {
        return false;
    }

}
