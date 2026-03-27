package map;

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
            int rowLength = territories[i].length - alternator;

            for (int j = 0; j < rowLength; j++) {
                Territory current = territories[i][j];

                if (current == null) {
                    continue;
                }

                // get territories left neighbor (if exists)
                if (j - 1 >= 0) {
                    addTerritoryNeighbor(current, territories[i][j - 1]);
                }

                // get right neighbor (if exists)
                if (j + 1 < rowLength) {
                    addTerritoryNeighbor(current, territories[i][j + 1]);
                }

                // get any upper neighbors
                if (i - 1 >= 0) {
                    // calculate the row above its length
                    int upperAlternator = ((i - 1) % 2 != 0) ? 1 : 0;
                    int upperRowLength = territories[i - 1].length - upperAlternator;
                    // are current row is an even row
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
                    int lowerAlternator = ((i + 1) % 2 != 0) ? 1 : 0;
                    int lowerRowLength = territories[i + 1].length - lowerAlternator;
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
                            // shift j over right 1 to grab the night neighbor
                            addTerritoryNeighbor(current, territories[i + 1][j + 1]);
                        }
                    }
                }


            }
        }
    }

    private void addTerritoryNeighbor(Territory current, Territory neighbor) {
        if (neighbor == null || current == null) return;

        if (!current.hasNeighbor(neighbor)) {
            current.addToTerritoryNeighbors(neighbor);
        }

        if (!neighbor.hasNeighbor(current)) {
            neighbor.addToTerritoryNeighbors(current);
        }
    }
}
