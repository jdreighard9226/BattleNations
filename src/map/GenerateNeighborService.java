package map;

import terrain.WaterRouteTerrain;

import java.util.List;

public class GenerateNeighborService {

    /**
     *
     * We have 6 possibles neighbors for any given territory.
     * We must check directly left, directly right, top left, top right, bottom left, bottom right
     *
     * @param territories
     */
    public void generateNeighbors(Territory[][] territories) {
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            // row length changes depending on if territories have been shifted to fit
            // even rows = territories[i].length
            // odd rows = territories[i].length - 1
            int rowLength = territories[i].length;

            for (int j = 0; j < rowLength; j++) {
                Territory current = territories[i][j];

                if (current == null) {
                    continue;
                }

                // get directly above neighbor (if exists)
                if (i - 2 >= 0) {
                    int upperVerticalRowLength = territories[i - 2].length;

                    if (j < upperVerticalRowLength) {
                        addTerritoryNeighbor(current, territories[i - 2][j]);
                    }
                }

                // get directly below neighbor (if exists)
                if (i + 2 < territories.length) {
                    int lowerVerticalRowLength = territories[i + 2].length;

                    if (j < lowerVerticalRowLength) {
                        addTerritoryNeighbor(current, territories[i + 2][j]);
                    }
                }

                // get any upper neighbors
                if (i - 1 >= 0) {
                    int upperRowLength = territories[i - 1].length;

                    // current row is an even row
                    if (alternator == 0) {
                        if (j - 1 >= 0 && j - 1 < upperRowLength) {
                            // add top left territory
                            addTerritoryNeighbor(current, territories[i - 1][j - 1]);
                        }

                        if (j < upperRowLength) {
                            // add top right
                            addTerritoryNeighbor(current, territories[i - 1][j]);
                        }
                    } else {
                        // current row is an odd row
                        if (j < upperRowLength) {
                            addTerritoryNeighbor(current, territories[i - 1][j]);
                        }

                        if (j + 1 < upperRowLength) {
                            addTerritoryNeighbor(current, territories[i - 1][j + 1]);
                        }
                    }
                }

                // get any lower neighbors
                if (i + 1 < territories.length) {
                    int lowerRowLength = territories[i + 1].length;

                    // the current row is an even row
                    if (alternator == 0) {
                        if (j - 1 >= 0 && j - 1 < lowerRowLength) {
                            // shift j over one to grab the true left neighbor
                            addTerritoryNeighbor(current, territories[i + 1][j - 1]);
                        }

                        if (j < lowerRowLength) {
                            // use j to grab the true right neighbor, no shift needed
                            addTerritoryNeighbor(current, territories[i + 1][j]);
                        }
                    } else {
                        // the current row is odd

                        if (j < lowerRowLength) {
                            addTerritoryNeighbor(current, territories[i + 1][j]);
                        }

                        if (j + 1 < lowerRowLength) {
                            // shift j over right 1 to grab the right neighbor
                            addTerritoryNeighbor(current, territories[i + 1][j + 1]);
                        }
                    }
                }
            }
        }
        addingWaterRouteNeighbors(territories);
    }

    private boolean addTerritoryNeighbor(Territory current, Territory neighbor) {
        if (neighbor == null || current == null) {
            return false;
        }

        boolean returnValue = false;
        if (!current.hasNeighbor(neighbor)) {
            current.addToTerritoryNeighbors(neighbor);
            returnValue = true;
        }

        if (!neighbor.hasNeighbor(current)) {
            neighbor.addToTerritoryNeighbors(current);
            returnValue = true;
        }
        return returnValue;
    }

    private void addingWaterRouteNeighbors(Territory[][] territories) {
        for (int i = 0; i < territories.length; i++) {
            int alternator = (i % 2 != 0) ? 1 : 0;
            // row length changes depending on if territories have been shifted to fit
            // even rows = territories[i].length
            // odd rows = territories[i].length - 1
            int rowLength = territories[i].length;

            for (int j = 0; j < rowLength; j++) {
                Territory current = territories[i][j];

                if (current == null) {
                    continue;
                }

                moreWaterRoutes(current);


            }
        }
    }

    private void moreWaterRoutes(Territory current) {
        List<Territory> neighbors = current.getNeighboringTerritories();
        boolean newNeighbors = false;
        outer:
        while (!newNeighbors) {
            newNeighbors = true;
            for (Territory n : neighbors) {
                if (n.getTerrain() instanceof WaterRouteTerrain) {
                    List<Territory> waterRoutesNeighbors = n.getNeighboringTerritories();
                    for (Territory wrn : waterRoutesNeighbors) {
                        if (addTerritoryNeighbor(current, wrn)) {
                            newNeighbors = false;
                            continue outer;
                        }
                    }
                }
            }
        }
    }
}