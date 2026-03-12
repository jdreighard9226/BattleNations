package GUI; //rename packages

import map.Terrain;
import map.TerrainType;
import map.Territory;

import javax.swing.*;
import java.awt.*;

public class MapDisplay {
    private final int displayWidth;
    private final int displayHeight;
    private final MapPanel mapDisplay;
    private Dimension screen;
    private Territory[][] territories;
    private StartController startController;
    private JFrame parent;

    public MapDisplay() {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = screen.width;
        displayHeight = screen.height;
        makeTerritories(18,5);
        mapDisplay = new MapPanel(territories);
        mapDisplay.setLayout(null);
        mapDisplay.setBounds(0,0,displayWidth,displayHeight);
        mapDisplay.repaint();
    }

    public void makeTerritories(int numberRows, int numberColumns) {
        double angleRadians = Math.toRadians(60);
        double cosValue = Math.cos(angleRadians);
        double sinValue = Math.sin(angleRadians);
        double length1 = (screen.getWidth() / (2 * numberColumns)) / (cosValue + 1) ;
        double length2 = (screen.getHeight() / (sinValue*numberRows));
        double length = length1;
        if (length1 > length2) {
            length = length2;
        }
        double xLocation = 0;
        double yLocation = sinValue*length;
        territories =  new Territory[numberRows][numberColumns];
        double[][] coordinates = new double[6][2];
        for (int i = 0; i < numberRows; i++) {
            int alternator = 0;
            if (i%2 != 0) {
                alternator = 1;
            }
            if (alternator == 0) {
                xLocation = 0;
            } else {
                xLocation = cosValue * length + length;
            }
            for (int j = 0; j < numberColumns - alternator; j++) {
                coordinates[0][0] = xLocation;
                coordinates[1][0] = cosValue*length+xLocation;
                coordinates[2][0] = coordinates[1][0] + length;
                coordinates[3][0] = cosValue*length+ coordinates[2][0];
                coordinates[4][0] = coordinates[2][0];
                coordinates[5][0] = coordinates[1][0];

                coordinates[0][1] = yLocation;
                coordinates[1][1] = yLocation - sinValue * length;
                coordinates[2][1] = coordinates[1][1];
                coordinates[3][1] = coordinates[0][1];
                coordinates[4][1] = yLocation + sinValue * length;
                coordinates[5][1] = coordinates[4][1];

                int[] xPoints = new int[6];
                int[] yPoints = new int[6];
                for (int k = 0; k < 6; k++) {
                    xPoints[k] = (int) Math.round(coordinates[k][0]);
                    yPoints[k] = (int) Math.round(coordinates[k][1]);
                }
                territories[i][j] = new Territory(TerrainType.CITY.getTerrain(), xPoints, yPoints);

                System.out.println(xLocation +" "+ yLocation);
                xLocation += cosValue*length*2 + length * 2;
            }
            yLocation += sinValue * length;
        }
    }

    public void addMapDisplay(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(mapDisplay);
        parent.repaint();
    }






}
