package terrain;

public class WaterTerrain extends Terrain {
    /**
     * Attack bonus percentage provided by city terrain.
     */
    private static final double ATTACK_BONUS_PERCENTAGE = 0;
    /**
     * Defense bonus percentage provided by city terrain.
     */
    private static final double DEFENSE_BONUS_PERCENTAGE = 0;
    /**
     * Additional troops granted by city terrain.
     */
    private static final int TROOP_BONUS = 0;
    /**
     * Image file used to represent city terrain on the map.
     */
    private static final String ICON_FILENAME = "src/gameImages/WaterTerrain.png";

    /**
     * Constructs a CityTerrain object with predefined terrain bonuses.
     */
    public WaterTerrain() {
        super(ATTACK_BONUS_PERCENTAGE, DEFENSE_BONUS_PERCENTAGE, ICON_FILENAME, TROOP_BONUS);
    }

}
