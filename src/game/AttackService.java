package game;

import map.Territory;

public class AttackService {
    public AttackService() {
    }

    public boolean attack(Territory territoryAttacking, Territory territoryDefending) {
        validateAttack(territoryAttacking, territoryDefending);
        double attackStrength = calculateEffectiveAttackStrength(territoryAttacking);
        double defenseStrength = calculateEffectiveDefenseStrength(territoryDefending);

        double attackingPlayerWinningOdds = attackStrength / (attackStrength + defenseStrength);
        double generatedWinningOdds = Math.random();


        // if attacker wins
        if (attackingPlayerWinningOdds >= generatedWinningOdds) {
            int originalAttackingTroops = territoryAttacking.getTroopAmount();
            int survivingAttackingTroops = calculateNewTroopAmount(originalAttackingTroops);

            territoryAttacking.setTroopAmount(1);
            territoryDefending.setPlayer(territoryAttacking.getPlayer());
            territoryDefending.setTroopAmount(survivingAttackingTroops);

            return true;
        } else {
            int originalAttackingTroops = territoryAttacking.getTroopAmount();
            int newTroops = calculateNewTroopAmount(originalAttackingTroops);
            territoryAttacking.setTroopAmount(newTroops);

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
        int troopsLostInBattle = (int) (Math.random() * (troopAmount));
        int newTroopAmount = troopAmount - troopsLostInBattle - 1;

        if (newTroopAmount > 0) {
            return newTroopAmount;
        } else {
            return 1;
        }
    }

    private void validateAttack(Territory attackingTerritory, Territory defendingTerritory) {
        if (attackingTerritory == null || defendingTerritory == null) {
            throw new IllegalArgumentException("Territories cannot be null");
        } else if (attackingTerritory.getPlayer() == null || defendingTerritory.getPlayer() == null) {
            throw new IllegalArgumentException("Both territories must have a player set");
        } else if (attackingTerritory.getPlayer().equals(defendingTerritory.getPlayer())) {
            throw new IllegalArgumentException("Both territories cannot be controlled by the same player");
        } else if (attackingTerritory.getTroopAmount() <= 1) {
            throw new IllegalArgumentException("Attacking territory must have at least 2 troops");
        }
    }

}