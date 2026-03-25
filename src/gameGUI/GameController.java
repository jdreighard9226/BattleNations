package gameGUI;

import game.AttackService;
import game.FortifyService;
import game.GameLogic;
import game.ReinforcementService;
import map.TotalDominationWorld;
import player.Player;

import javax.swing.*;
import java.util.List;

public class GameController {
    private GameLogic gameLogic;
    public GameController(TotalDominationWorld world, List<Player> players, JFrame display){
        gameLogic = new GameLogic(world, players, new AttackService(), new ReinforcementService(), new FortifyService());
        System.exit(0);
    }
}
