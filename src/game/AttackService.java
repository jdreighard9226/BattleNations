package game;
import map.Territory;

public class AttackService {
    public AttackService() {
    }

    public boolean attack(Territory territoryAttacking, Territory territoryDefending) {
        double attackStrength = calculateEffectiveAttackStrength(territoryAttacking);
        double defenseStrength = calculateEffectiveDefenseStrength(territoryDefending);

        double attackingPlayerWinningOdds = attackStrength / (attackStrength + defenseStrength);
        double generatedWinningOdds = Math.random();


        // if attacker wins
        if (attackingPlayerWinningOdds >= generatedWinningOdds) {
            int originalAttackingTroops = territoryAttacking.getTroopAmount();
            int newTroops = calculateNewTroopAmount(originalAttackingTroops);
            territoryAttacking.setTroopAmount(1);
            territoryDefending.setPlayer(territoryAttacking.getPlayer());
            territoryDefending.setTroopAmount(newTroops);

            return true;
        } else {
            // attacker lost
            int originalDefendingTroops = territoryDefending.getTroopAmount();
            int newTroops = calculateNewTroopAmount(originalDefendingTroops);
            territoryDefending.setTroopAmount(newTroops);
            return false;
    }
}

    private double calculateEffectiveAttackStrength(Territory territory) {
        return territory.getTroopAmount() * (1 + territory.getTerrain().getAttackBonus());
    }

    private double calculateEffectiveDefenseStrength(Territory territory) {
        return territory.getTroopAmount() * (1 + territory.getTerrain().getDefenseBonus());
    }

    private int calculateNewTroopAmount(int troopAmount) {
        int troopsLostInBattle = (int)(Math.random() * (troopAmount + 1));
        return troopAmount - troopsLostInBattle - 1;
    }
}
