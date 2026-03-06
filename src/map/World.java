package map;

import java.util.List;

public interface World {
    List<Region> getRegions();
    boolean isGameOver();
}
