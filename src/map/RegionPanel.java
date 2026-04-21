package map;

import setUpGUI.SetUpController;
import startGUI.StartController;
import terrain.WaterRouteTerrain;
import terrain.WaterTerrain;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegionPanel extends JPanel {

    private final List<Region> regions;

    private final Territory[][] territories;

    public RegionPanel(Territory[][] territories, List<Region> regions, SetUpController setUpController) {
        this.territories = territories;
        this.regions = regions;

        //Sets layout to null and gets screen size.
        this.setLayout(null);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        // Button that returns program to start when pressed.
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBounds(screen.width - 52, 2, 50, 50);
        closeButton.addActionListener(e -> {
            setUpController.reset();
        });
        this.add(closeButton);

        // Button that minimizes program when pressed.
        JButton minimizeButton = new JButton("-");
        minimizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        minimizeButton.setBounds(screen.width - 104, 2, 50, 50);
        minimizeButton.addActionListener(e -> {
            setUpController.minimize();
        });
        this.add(minimizeButton);
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
