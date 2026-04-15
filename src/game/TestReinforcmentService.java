package game;

import map.Territory;
import player.Player;
import terrain.TerrainType;

import java.awt.*;

public class TestReinforcmentService {

    public static void main(String[] args) {

    }

    private static boolean testValidReinforcement() {
        System.out.print("Testing valid reinforce: ");
        boolean passed = true;
        Player player = new Player("p1", Color.black);
        Territory t1 = new Territory(player, TerrainType.DESERT.getTerrain(), 20, new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 2, 3, 4, 5, 6}, false);
        int troopsToPlace = 20;
        player.setTroopsToPlace(troopsToPlace);
        ReinforcementService reinforcementService = new ReinforcementService();

        for (int i = 0; i < 100; i++) {
           ValidationResult result =  reinforcementService.reinforce(player, t1, troopsToPlace);
           if (!result.isValid()) {
               passed = false;
               System.out.println("reinforce returned false, on a valid reinforcement call");
           }
        }
        if (passed) {
            System.out.println("Passed");
        }  else {
            System.err.println("Failed");
        }
        return passed;
    }

}
