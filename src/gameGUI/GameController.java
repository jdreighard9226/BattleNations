package gameGUI;

import game.AttackService;
import game.FortifyService;
import game.GameLogic;
import game.ReinforcementService;
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
//                    if (faze is something)) {
//                        do this function;
//                    }
//                    else if (if face is something else){
//                        do this fuctnion;
//                    }
                }
            }

    }
}
