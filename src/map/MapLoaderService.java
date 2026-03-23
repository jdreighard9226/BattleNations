package map;

import terrain.TerrainType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoaderService {
    public MapLoaderData loadTerritories(String fileName, Dimension screen) {
        File mapFile = new File(fileName);
        int numberRows;
        int numberColumns;
        int numberRegions;
        List<Region> regions = new ArrayList<Region>();
        String[][] terrainTypes;
        Scanner fileReader = null;

        try {
            System.out.println(mapFile);
            fileReader = new Scanner(mapFile);
            numberRows = fileReader.nextInt();
            numberColumns = fileReader.nextInt();
            numberRegions = fileReader.nextInt();
            terrainTypes = new String[numberRows][numberColumns];

            // Read terrain types from file
            for (int i = 0; i < numberRows; i++) {
                int alternator = (i % 2 != 0) ? 1 : 0;
                for (int j = 0; j < numberColumns - alternator; j++) {
                    terrainTypes[i][j] = fileReader.next();
                    if (!terrainTypes[i][j].equals("W")) {
                        String region = fileReader.next();
                        if (terrainTypes[i][j].equals("C")) {
                            terrainTypes[i][j] += " " + region + " " + fileReader.next();
                        }
                        else {
                            terrainTypes[i][j] += " " + region;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < numberRegions; i++) {
            regions.add(new Region());
        }

        // Calculate hex size based on screen dimensions
        double angleRadians = Math.toRadians(60);
        double cosValue = Math.cos(angleRadians);
        double sinValue = Math.sin(angleRadians);
        double length1 = (screen.getWidth() / (2 * numberColumns)) / (cosValue + 1);
        double length2 = (screen.getHeight() / (sinValue * (numberRows + 1)));
        double length = Math.min(length1, length2);

        // Starting coordinates for hex grid
        double xLocation = 0;
        double yLocation = sinValue * length;

        Territory[][] territories = new Territory[numberRows][numberColumns];
        territories = new Territory[numberRows][numberColumns];
        double[][] coordinates = new double[6][2];

        // Generate hexagonal territories
        for (int i = 0; i < numberRows; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            xLocation = (alternator == 0) ? 0 : cosValue * length + length;

            for (int j = 0; j < numberColumns - alternator; j++) {
                // Compute hexagon vertices
                coordinates[0][0] = xLocation;
                coordinates[1][0] = cosValue * length + xLocation;
                coordinates[2][0] = coordinates[1][0] + length;
                coordinates[3][0] = cosValue * length + coordinates[2][0];
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

                // Assign terrain type to hex
                switch (terrainTypes[i][j].substring(0,1)) {
                    case "M":
                        territories[i][j] = new Territory(TerrainType.MOUNTAIN.getTerrain(), xPoints, yPoints, false);
                        regions.get(Integer.parseInt(terrainTypes[i][j].substring(2)) - 1).addTerritory(territories[i][j]);
                        break;
                    case "D":
                        territories[i][j] = new Territory(TerrainType.DESERT.getTerrain(), xPoints, yPoints, false);
                        regions.get(Integer.parseInt(terrainTypes[i][j].substring(2)) - 1).addTerritory(territories[i][j]);
                        break;
                    case "C":
                        boolean isCapital = terrainTypes[i][j].substring(4).equals("T");
                        territories[i][j] = new Territory(TerrainType.CITY.getTerrain(), xPoints, yPoints, isCapital);
                        regions.get(Integer.parseInt(terrainTypes[i][j].substring(2,3)) - 1).addTerritory(territories[i][j]);
                        break;
                    case "W":
                        territories[i][j] = new Territory(TerrainType.WATER.getTerrain(), xPoints, yPoints, false);
                        break;
                    case "P":
                        territories[i][j] = new Territory(TerrainType.PLAIN.getTerrain(), xPoints, yPoints, false);
                        regions.get(Integer.parseInt(terrainTypes[i][j].substring(2)) - 1).addTerritory(territories[i][j]);
                }

                // Move to next hex horizontally
                xLocation += cosValue * length * 2 + length * 2;
            }

            // Move to next hex row
            yLocation += sinValue * length;
        }
        return new MapLoaderData(territories, regions);
    }
}
