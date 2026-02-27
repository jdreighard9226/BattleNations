package map;

public class MountainTerrain extends TerrainType {
    private static final double ATTACK_BONUS = 0;
    private static final double DEFENSE_BONUS = .1;
    private static final int TROOP_BONUS = 0;
    private static final String ICON_FILENAME = "PlaceHolder";

    public MountainTerrain() {
        super(ATTACK_BONUS, DEFENSE_BONUS, ICON_FILENAME, TROOP_BONUS);
    }
}
