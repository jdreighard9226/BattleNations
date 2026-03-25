package game;

import map.Territory;

public class FortifyService {

    public FortifyService() {
    }

    public ValidationResult fortify(Territory territoryToTakeTroopsFrom, Territory territoryToAddTroopsTo, int troopsBeingMoved) {

        ValidationResult result = validateFortify(territoryToTakeTroopsFrom, territoryToAddTroopsTo, troopsBeingMoved);
        if (!result.isValid()) {
            return result;
        }
        territoryToTakeTroopsFrom.setTroopAmount(territoryToTakeTroopsFrom.getTroopAmount() - troopsBeingMoved);
        territoryToAddTroopsTo.setTroopAmount(territoryToAddTroopsTo.getTroopAmount() + troopsBeingMoved);
        return new ValidationResult(true, "Fortify Move made.");
    }

    public ValidationResult validateFortify(Territory territoryToTakeTroopsFrom, Territory territoryToAddTroopsTo, int troopsBeingMoved) {
        if (territoryToTakeTroopsFrom.getPlayer() == null || territoryToAddTroopsTo.getPlayer() == null) {
            return new ValidationResult(false, "Territories cannot be null");
        } else if (!territoryToTakeTroopsFrom.getPlayer().equals(territoryToAddTroopsTo.getPlayer())) {
            return new ValidationResult(false, "Player must own both territories to move troops");
        } else if (territoryToTakeTroopsFrom.getTroopAmount() <= troopsBeingMoved) {
            return new ValidationResult(false, "Not enough troops to move");
        } else if (troopsBeingMoved < 1) {
            return new ValidationResult(false, "At least 1 troop Must be moved");
        }
        return new ValidationResult(true, "Valid Fortify Move Has been made");
    }
}
