package map;

import player.Player;

import java.util.List;

public class TotalDominationWorld extends AbstractWorld {
    public TotalDominationWorld(List<Region> regions) {
        super(regions);
    }

    public TotalDominationWorld() {
        super();
    }

    @Override
    public boolean isGameWon(List<Player> players) {
        for (Player player : players) {
            // ensure all regions are conquered at least by someone
            if (getRegionCountControlledByPlayer(player) == regions.size()) {
                return true;
            }

        }
        return false;
    }
}
