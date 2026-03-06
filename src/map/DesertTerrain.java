package map;

public class DesertTerrain extends Terrain {
    private static final double ATTACK_BONUS_PERCENTAGE = .15;
    private static final double DEFENSE_BONUS_PERCENTAGE = 0;
    private static final int TROOP_BONUS = 0;
    private static final String ICON_FILENAME = "PlaceHolder";

    public DesertTerrain() {
        super(ATTACK_BONUS_PERCENTAGE, DEFENSE_BONUS_PERCENTAGE, ICON_FILENAME, TROOP_BONUS);
    }
}
