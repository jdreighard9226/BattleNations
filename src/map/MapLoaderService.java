package map;

import terrain.TerrainType;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Creates a list of territories and regions from a text file.
 *
 * <p>This class serves to take as input a text file, and then create from it a list of territories and regions that
 * can then be used for gameplay logic and display later.</p>
 */
public class MapLoaderService {
    /**
     * Takes a string filename as input and the dimensions of the screen. It then reads through the text file to
     * populate the territories list. Using the screen dimensions it grids the territories to fit the screen size.
     *
     * @param fileName the file that holds the map information.
     * @param screen the dimensions of the screen that the map is being fit too.
     * @return the MapLoaderData object that holds a doubly indexed array of territories, and a list of regions.
     */
    public MapLoaderData loadTerritories(String fileName, Dimension screen) {
        //Creates an instance of a helper class that will be used later.
        GenerateNeighborService generateNeighborService = new GenerateNeighborService();

        //Creates all the variables that will be needed.
        int numberRows;
        int numberColumns;
        int numberRegions;
        List<Region> regions = new ArrayList<>();
        String[][] terrainTypes;

        // Creates the scanner and file that will be needed to read the file.
        Scanner fileReader;
        File mapFile = new File(fileName);


        try {
            // Reads the first line of the file in segments, filling out the information for number of rows, columns and
            // regions.
            fileReader = new Scanner(mapFile);
            numberRows = fileReader.nextInt();
            numberColumns = fileReader.nextInt();
            numberRegions = fileReader.nextInt();

            // Creates a new doubly indexed array for storing the information that is read from the file for each territory.
            terrainTypes = new String[numberRows][numberColumns];

            // Read terrain types from file
            for (int i = 0; i < numberRows; i++) {
                // Alternator is used because hexagon griding results in every other row having one less column.
                int alternator = (i % 2 != 0) ? 1 : 0;
                for (int j = 0; j < numberColumns - alternator; j++) {
                    terrainTypes[i][j] = fileReader.next();
                    // If the terrain type is "W" (water) there is no more information that needs to be associated with it,
                    // so it can go on to the next index before reading anything more from the file.
                    if (!terrainTypes[i][j].equals("W")) {
                        String region = fileReader.next();
                        // If the terrain is "C" (city) there is an extra piece of information that is associated with it,
                        // so the file reader must read one more line to collect it before going on to the next index.
                        if (terrainTypes[i][j].equals("C")) {
                            // Creates a string at the index location with all the information needed for the territory.
                            terrainTypes[i][j] += " " + region + " " + fileReader.next();
                        }
                        else {
                            // Creates a string at the index location with all the information needed for the territory.
                            terrainTypes[i][j] += " " + region;
                        }
                    }
                }
            }
            //Throw actual error or do something.
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Creates the number of regions that was specified in the text file.
        for (int i = 0; i < numberRegions; i++) {
            regions.add(new Region());
        }

        // Calculates length of each side of the hexagon based on the screen size.

        // Converts the 60 degrees into radians so that cos and sin values
        // can be calculated.
        double angleRadians = Math.toRadians(60);
        double cosValue = Math.cos(angleRadians);
        double sinValue = Math.sin(angleRadians);

        // Calculates what the length of each side would be if the width was the restraining dimension.
        // The equation is derived from the equation of finding what length it would take to fill the entire
        // screen width.
        //     screenWidth = 2 * numberColumns * (cos(60) * length + length) - length.
        // The last - length is present because each columns width is calculated by taking a hexagons width, and that of
        // the one bellow it diagonally to the right, but the last column does not have a hexagon bellow it to the right diagonally,
        // thus one length must be subtracted.
        double length1 = (screen.getWidth() / (2*numberColumns*(cosValue + 1) - 1));

        // Calculates what the length of each side would be if the height was the restraining dimension.
        // The equation is derived from the equation
        double length2 = (screen.getHeight() / (sinValue * (numberRows + 1))) * 0.8;

        // Takes the smaller length value, the restraining value and sets it as the length value for all sides of the hexagons.
        double length = Math.min(length1, length2);

        // Finds how much extra room there is in the x direction and centers it, by using the previous width dimension
        // used, plugging in length, subtracting that value from the screens width, and then dividing it by two, so that
        // we get the offset value which will correspond to how much space we will have on either side of the map.
        double offset = (screen.getWidth() - (length * (2*numberColumns*(cosValue+1)-1)))/2;

        // Initializes starting y and x locations.
        double xLocation;
        double yLocation = sinValue * length;

        // Creates an empty doubly indexed array to store the territories made.
        Territory[][] territories = new Territory[numberRows][numberColumns];

        // Creates an empty doubly indexed array to store the coordinates for each territory as its made.
        double[][] coordinates = new double[6][2];

        // Generate hexagonal territories.
        for (int i = 0; i < numberRows; i++) {
            // Every other row in a hexagon grid has one less column.
            int alternator = (i % 2 != 0) ? 1 : 0;
            // If it's on the odd column, it must position the starting x coordinate to the bottom right of the previous
            // rows starting hexagon.
            xLocation = (alternator == 0) ? 0 + offset: cosValue * length + length + offset;

            for (int j = 0; j < numberColumns - alternator; j++) {
                // Calculates the x coordinates of the hexagon
                coordinates[0][0] = xLocation;
                coordinates[1][0] = cosValue * length + xLocation;
                coordinates[2][0] = coordinates[1][0] + length;
                coordinates[3][0] = cosValue * length + coordinates[2][0];
                coordinates[4][0] = coordinates[2][0];
                coordinates[5][0] = coordinates[1][0];

                // Calculates the y coordinates of the hexagon.
                coordinates[0][1] = yLocation;
                coordinates[1][1] = yLocation - sinValue * length;
                coordinates[2][1] = coordinates[1][1];
                coordinates[3][1] = coordinates[0][1];
                coordinates[4][1] = yLocation + sinValue * length;
                coordinates[5][1] = coordinates[4][1];

                // Creates two new arrays to store the coordinates in.
                int[] xPoints = new int[6];
                int[] yPoints = new int[6];
                for (int k = 0; k < 6; k++) {
                    // Converts the double values into int values by rounding so that the values can be used for drawing
                    // in java swing.
                    xPoints[k] = (int) Math.round(coordinates[k][0]);
                    yPoints[k] = (int) Math.round(coordinates[k][1]);
                }

                // Creates a territory object and adds it to the territory array and the regions list as needed.
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

                // Move to next hexagon horizontally
                xLocation += cosValue * length * 2 + length * 2;
            }

            // Move to next hexagon row
            yLocation += sinValue * length;
        }
        // populate every territory neighboring territories
        generateNeighborService.generateNeighbors(territories);
        return new MapLoaderData(territories, regions);
    }
}
