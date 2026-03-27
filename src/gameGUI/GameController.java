package gameGUI;

import game.*;
import map.MapPanel;
import map.Territory;
import map.TotalDominationWorld;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameController {
    private GameLogic gameLogic;
    private List<Territory> territories;
    public GameController(TotalDominationWorld world, List<Player> players, JFrame display, MapPanel mapPanel){
        gameLogic = new GameLogic(world, players, new AttackService(), new ReinforcementService(), new FortifyService());
        territories = world.getAllTerritories();
        mapPanel.switchToGameController(this);
        System.exit(0);
    }

    public void getTerritoryClicked(Point point) {
            for (Territory territory : territories) {
                if (territory != null && territory.contains(point)) {
                    if (gameLogic.getCurrentPhase() == TurnPhase.REINFORCEMENT) {
                        // do something
                        return;
                    } else if (gameLogic.getCurrentPhase() == TurnPhase.ATTACK) {
                        // do something
                        return;
                       // game logic is Fortify
                    } else {
                        // do something
                        return;
                    }
                }
            }

    }
}
