package game;

import map.Territory;
import map.World;
import player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private final World world;
    private final List<Player> players;
    private final AttackService attackService;
    private final ReinforcementService reinforcementService;
    private final FortifyService fortifyService;
    private TurnPhase currentPhase;

    public GameLogic(World world, List<Player> players, AttackService attackService, ReinforcementService reinforcementService, FortifyService fortifyService) {
        this.world = world;
        this.players = new ArrayList<>(players);
        this.attackService = attackService;
        this.reinforcementService = reinforcementService;
        this.fortifyService = fortifyService;
        this.currentPhase = TurnPhase.REINFORCEMENT;
    }
    public ValidationResult attack(Territory attackingTerritory, Territory defendingTerritory) {
        return attackService.attack(attackingTerritory, defendingTerritory);
    }

    public void fortify(Territory territoryToTakeTroopsFrom, Territory territoryToAddTroopsTo, int troopsBeingMoved) {
        fortifyService.fortify(territoryToTakeTroopsFrom, territoryToAddTroopsTo, troopsBeingMoved);
    }

    public void reinforce(Player player, Territory territory, int troopsToAdd) {
        reinforcementService.reinforce(player, territory, troopsToAdd);
    }

    public int calculateReinforcement(Player player) {
        return reinforcementService.calculateReinforcements(player, world);
    }

    public void changePhase() {
        if (currentPhase == TurnPhase.REINFORCEMENT) {
            currentPhase = TurnPhase.ATTACK;
        } else if (currentPhase == TurnPhase.ATTACK) {
            currentPhase = TurnPhase.FORTIFY;
        } else {
            currentPhase = TurnPhase.REINFORCEMENT;
        }
    }

    public TurnPhase getCurrentPhase() {
        return currentPhase;
    }

    public World getWorld() {
        return world;
    }
    public List<Player> getPlayers() {
        return players;
    }
}
