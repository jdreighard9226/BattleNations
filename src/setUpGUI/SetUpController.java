package setUpGUI;

import startGUI.*;

import javax.swing.*;

public class SetUpController {
    private final JFrame display;

    private final SetUpData gameSetUpData;

    private final MapDisplay mapDisplay;

    public SetUpController(JFrame display, SetUpData setUpData) {
        this.display = display;
        this.gameSetUpData = setUpData;

        mapDisplay = new MapDisplay(this);
        displayMapDisplay();
    }

    public JFrame getDisplay() {
        return display;
    }

    public SetUpData getGameSetUpData() {
        return gameSetUpData;
    }

    public void displayMapDisplay() {
        mapDisplay.addMapDisplay();
    }
}
