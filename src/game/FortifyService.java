package game;

import map.Territory;

public class FortifyService {

    public FortifyService() {
    }

    public void fortify(Territory territoryToTakeTroopsFrom, Territory territoryToAddTroopsTo, int troopsBeingMoved) {
        validateFortify(territoryToTakeTroopsFrom, territoryToAddTroopsTo, troopsBeingMoved);
        territoryToTakeTroopsFrom.setTroopAmount(territoryToTakeTroopsFrom.getTroopAmount() - troopsBeingMoved);
        territoryToAddTroopsTo.setTroopAmount(territoryToAddTroopsTo.getTroopAmount() + troopsBeingMoved);
    }

    public void validateFortify(Territory territoryToTakeTroopsFrom, Territory territoryToAddTroopsTo, int troopsBeingMoved) {
        if (territoryToTakeTroopsFrom.getPlayer() == null || territoryToAddTroopsTo.getPlayer() == null) {
            throw new IllegalArgumentException("Territories cannot have null player");
        } else if (!territoryToTakeTroopsFrom.getPlayer().equals(territoryToAddTroopsTo.getPlayer())) {
            throw new IllegalArgumentException("Player must own both territories to move troops");
        } else if (territoryToTakeTroopsFrom.getTroopAmount() <= troopsBeingMoved) {
            throw new IllegalArgumentException("Too many troops were being moved out of a territory");
        } else if (troopsBeingMoved < 1) {
            throw new IllegalArgumentException("At least 1 troop must be moved");
        }
    }
}
