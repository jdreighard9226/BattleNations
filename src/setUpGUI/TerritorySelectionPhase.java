package setUpGUI;

import map.Territory;
import player.Player;
import terrain.WaterRouteTerrain;
import terrain.WaterTerrain;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * Handles the territory selection phase of the Battle Nations setup process.
 *
 * <p>
 * This class manages both manual and random assignment of territories to players.
 * It controls turn order during selection, validates valid territory placement,
 * and transitions to the troop placement phase once all valid territories are assigned.
 * </p>
 *
 * <p>
 * Water and water-route territories cannot be selected and are automatically excluded
 * from assignment logic.
 * </p>
 */
public class TerritorySelectionPhase {

    /** Controller used to manage UI updates and game state transitions. */
    private final SetUpController setUpController;

    /** 2D grid representing all territories on the map. */
    private final Territory[][] territories;

    /** The player whose turn it currently is for territory selection. */
    private Player activePlayer;

    /** List of all players participating in the game. */
    private final List<Player> players;

    /** Tracks which player's turn index is active. */
    private int playerTurn = 0;

    /**
     * Constructs the territory selection phase and optionally assigns territories randomly.
     *
     * <p>
     * If random assignment is enabled, all valid territories are distributed immediately
     * among players before manual selection begins.
     * </p>
     *
     * @param setUpController controller used for UI updates and phase transitions
     * @param territories 2D grid of map territories
     * @param players list of players participating in the game
     * @param isRandom whether territories should be randomly assigned
     */
    public TerritorySelectionPhase(SetUpController setUpController, Territory[][] territories, List<Player> players, Boolean isRandom) {
        this.setUpController = setUpController;
        this.territories = territories;
        this.players = players;
        this.activePlayer = players.getFirst();
        if (isRandom) {
            randomlyAssigningTerritories();
        }
    }

    /**
     * Updates the UI text showing current game information for the selection phase.
     */
    public void initializeGameInfoText() {
        setUpController.updateGameInfoText();
    }

    /**
     * Handles a mouse click by checking if a territory was clicked and attempting assignment.
     *
     * @param point the screen coordinate of the click
     */
    public void dealWithClick(Point point) {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory != null && territory.contains(point)) {
                    assigningTerritory(territory);
                }
            }
        }
    }

    /**
     * Assigns the selected territory to the active player if it is valid.
     *
     * <p>
     * A territory can only be assigned if it is unowned and not water-based.
     * After assignment, turn order is advanced and the UI is updated.
     * </p>
     *
     * @param territory the territory being assigned
     */
    public void assigningTerritory(Territory territory) {

        // Checks to make sure the territory isn't already owned.
        if (territory.getPlayer() == null) {

            // Checks to make sure that the territory can be owned and isn't a forbidden type.
            if (territory.getTerrain() instanceof WaterTerrain || territory.getTerrain() instanceof WaterRouteTerrain) {
                setUpController.getErrorText().setText("Cannot select a water or water route Territory. Select A different one ");
                setUpController.getGameInfoPanel().repaint();
            } else {

                // Sets the active player as the territory owner and sets the territory troop amount to 1.
                territory.setPlayer(activePlayer);
                territory.setTroopAmount(1);

                // Passes the turn to the next player.
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
                setUpController.updateGameInfoText();

                // If there are no more unowned territories ends the phase.
                if (!unownedTerritory()) {
                    setUpController.placeTroopPhase();
                }
            }
        } else {
            setUpController.getErrorText().setText("Territory is already Owned, Select A different one");
            setUpController.getGameInfoPanel().repaint();
        }

    }

    /**
     * Checks whether there are any unowned valid territories remaining.
     *
     * @return true if at least one valid unowned territory exists, false otherwise
     */
    public boolean unownedTerritory() {
        for (Territory[] row : territories) {
            for (Territory territory : row) {
                if (territory != null && territory.getPlayer() == null && !(territory.getTerrain() instanceof WaterTerrain) && !(territory.getTerrain() instanceof WaterRouteTerrain)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Randomly assigns all valid territories to players in turn order.
     *
     * <p>
     * Water and water-route territories are excluded from assignment.
     * Once all territories are assigned, the game transitions to the troop placement phase.
     * </p>
     */
    public void randomlyAssigningTerritories() {
        Random random = new Random();
        while (unownedTerritory()) {

            // Generates a random territory location and checks to see if the territory located
            // there is owned or can be owned.
            int row = random.nextInt(territories.length);
            int col = random.nextInt(territories[row].length);
            if (territories[row][col] != null && territories[row][col].getPlayer() == null && !(territories[row][col].getTerrain() instanceof WaterTerrain) && !(territories[row][col].getTerrain() instanceof WaterRouteTerrain)) {

                // Assigns territory to the active player, and then passes the turn to the next player.
                territories[row][col].setPlayer(activePlayer);
                territories[row][col].setTroopAmount(1);
                playerTurn++;
                if (playerTurn >= players.size()) {
                    playerTurn = 0;
                }
                activePlayer = players.get(playerTurn);
            }
        }
        setUpController.placeTroopPhase();
    }

    /**
     * Gets the player whose turn it currently is in the selection phase.
     *
     * @return the active player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }
}
