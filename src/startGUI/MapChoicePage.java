package startGUI;

import javax.swing.*;
import java.awt.*;

public class MapChoicePage {
    private JPanel mapChoicePanel;

    private JFrame parent;

    private StartController startController;

    public MapChoicePage() {
        mapChoicePanel = new ImagePanel("src/gameImages/OptionScreen.png");

        // Get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        mapChoicePanel.setLayout(null);
        mapChoicePanel.setBounds(0, 0, screen.width, screen.height);

        ImageIcon map1 = new ImageIcon("src/gameImages/FunnyLandMapImage");
        JButton map1bt = new JButton(map1);
        map1bt.setBounds((int) screen.getWidth()/4, (int) screen.getHeight()/4, (int) screen.getWidth()/6, (int) screen.getHeight()/6);
        mapChoicePanel.add(map1bt);
    }

    public void addMapChoicePage(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(mapChoicePanel);
        parent.repaint();
    }
}
