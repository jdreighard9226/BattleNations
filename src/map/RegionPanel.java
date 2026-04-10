package map;

import terrain.WaterRouteTerrain;
import terrain.WaterTerrain;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegionPanel extends JPanel {

    private final List<Region> regions;

    private final Territory[][] territories;

    public RegionPanel(Territory[][] territories, List<Region> regions) {
        this.territories = territories;
        this.regions = regions;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Region region : regions) {
            region.draw(g);
        }

        for (Territory[] row : territories) {
            for (Territory t : row) {
                if (t != null && (t.getTerrain() instanceof WaterTerrain || t.getTerrain() instanceof WaterRouteTerrain)) {
                    t.Draw(g);
                }
            }
        }
    }
}
