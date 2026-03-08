package GUI; //rename packages

import javax.swing.*;
import java.awt.*;

public class MapDisplay {
    private final int displayWidth;
    private final int displayHeight;
    private final JPanel mapDisplay;

    public MapDisplay() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = screen.width;
        displayHeight = screen.height;

        mapDisplay = new JPanel();
        mapDisplay.setLayout(null);
        mapDisplay.setBounds(0,0,displayWidth,displayHeight);
    }

//    public void drawMap(int numberRows, int numberColumns) {
//        for
//
//    }



}
