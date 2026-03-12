package GUI;

import map.Territory;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    private final Territory[][] territories;

    public MapPanel(Territory[][] territories)
    {
        this.territories = territories;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (territories == null) return;

        for (Territory[] value : territories) {
            for (Territory territory : value) {
                if (territory != null) {
                    territory.Draw(g);
                }
            }
        }

    }

}
