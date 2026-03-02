package map;

public class DesertTerrain extends Terrain {
    private static final double ATTACK_BONUS = .15;
    private static final double DEFENSE_BONUS = 0;
    private static final int TROOP_BONUS = 0;
    private static final String ICON_FILENAME = "PlaceHolder";

    public DesertTerrain() {
        super(ATTACK_BONUS, DEFENSE_BONUS, ICON_FILENAME, TROOP_BONUS);
    }
}
