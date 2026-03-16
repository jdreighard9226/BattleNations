package terrain;

public class WaterRouteTerrain extends Terrain {
    /**
     * Attack bonus percentage provided by mountain terrain.
     */
    private static final double ATTACK_BONUS_PERCENTAGE = 0;
    /**
     * Defense bonus percentage provided by mountain terrain.
     */
    private static final double DEFENSE_BONUS_PERCENTAGE = 0;
    /**
     * Additional troops granted by mountain terrain.
     */
    private static final int TROOP_BONUS = 0;
    /**
     * Image file used to represent mountain terrain on the map.
     */
    private static final String ICON_FILENAME = "temp";

    /**
     * Constructs a MountainTerrain object with predefined terrain bonuses.
     */
    public WaterRouteTerrain() {
        super(ATTACK_BONUS_PERCENTAGE, DEFENSE_BONUS_PERCENTAGE, ICON_FILENAME, TROOP_BONUS);
    }
}