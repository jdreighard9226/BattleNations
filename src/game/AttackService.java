package game;

import map.Territory;

public class AttackService {
    public AttackService() {
    }

    public ValidationResult attack(Territory territoryAttacking, Territory territoryDefending) {
        ValidationResult validationResult = validateAttack(territoryAttacking, territoryDefending);
        if (!validationResult.isValid()) {
            return validationResult;
        }
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

            return new ValidationResult(true, "Attacker Wins");
        } else {
            int originalAttackingTroops = territoryAttacking.getTroopAmount();
            int newTroops = calculateNewTroopAmount(originalAttackingTroops);
            territoryAttacking.setTroopAmount(newTroops);

            return new ValidationResult(true, "Attacker Lost");
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

    private ValidationResult validateAttack(Territory attackingTerritory, Territory defendingTerritory) {
        if (attackingTerritory == null || defendingTerritory == null) {
            return new ValidationResult(false, "Territories cannot be null");
        } else if (attackingTerritory.getPlayer() == null || defendingTerritory.getPlayer() == null) {
            return new ValidationResult(false, "Both territories must have a player set");
        } else if (attackingTerritory.getPlayer().equals(defendingTerritory.getPlayer())) {
            return new ValidationResult(false,"Both territories cannot be controlled by the same player");
        } else if (attackingTerritory.getTroopAmount() <= 1) {
            return new ValidationResult(false,"Attacking territory must have at least 2 troops");
        } else {
            return new ValidationResult(true, "Valid Attack");
        }
    }
}