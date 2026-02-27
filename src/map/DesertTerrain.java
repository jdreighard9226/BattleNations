package map;

public class DesertTerrain extends TerrainType {
    private static final double ATTACK_BONUS = .15;
    private static final double DEFENSE_BONUS = 0;
    private static final int TROOP_BONUS = 0;
    private static final String FILENAME = "PlaceHolder";

    public DesertTerrain() {
        super(ATTACK_BONUS, DEFENSE_BONUS, FILENAME, TROOP_BONUS);
    }
}
