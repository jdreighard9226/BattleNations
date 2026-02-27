package map;

public class CityTerrain extends TerrainType {
    private static final double ATTACK_BONUS = .12;
    private static final double DEFENSE_BONUS = 0;
    private static final int TROOP_BONUS = 1;
    private static final String ICON_FILENAME = "PlaceHolder";

    public CityTerrain() {
        super(ATTACK_BONUS, DEFENSE_BONUS, ICON_FILENAME, TROOP_BONUS);
    }
}
