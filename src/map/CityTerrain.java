package map;

public class CityTerrain extends TerrainType {
    private static final double ATTACK_BONUS = .12;
    private static final double DEFENSE_BONUS = 0;
    private static final int TROOP_BONUS = 1;
    private static final String FILENAME = "PlaceHolder";

    public CityTerrain() {
        super(ATTACK_BONUS, DEFENSE_BONUS, FILENAME, TROOP_BONUS);
    }
}
