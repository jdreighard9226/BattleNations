package game;

import map.World;
import player.Player;

import java.util.List;

public class GameLogic {
    private final World world;
    private final List<Player> players;
    private final AttackService attackService;
    private final ReinforcementService reinforcementService;
    private final FortifyService fortifyService;

    public GameLogic(World world, List<Player> players, AttackService attackService, ReinforcementService reinforcementService, FortifyService fortifyService) {
        this.world = world;
        this.players = players;
        this.attackService = attackService;
        this.reinforcementService = reinforcementService;
        this.fortifyService = fortifyService;
    }
}
