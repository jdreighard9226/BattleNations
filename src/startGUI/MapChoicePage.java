package startGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapChoicePage {
    private JPanel mapChoicePanel;

    private JFrame parent;

    private String mapLocation;

    private StartController startController;

    public MapChoicePage() {
        mapChoicePanel = new ImagePanel("src/gameImages/OptionScreen.png");

        // Get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        mapChoicePanel.setLayout(null);
        mapChoicePanel.setBounds(0, 0, screen.width, screen.height);

        ButtonGroup mapChoices = new ButtonGroup();

        ImageIcon map1 = new ImageIcon("src/gameImages/FunnyLandMapImage.jpg");
        Image map1Image= map1.getImage();
        Image rescaledImageMap1 = map1Image.getScaledInstance((int) screen.getWidth()/4, (int) screen.getHeight()/4, Image.SCALE_SMOOTH);
        JRadioButton map1bt = new JRadioButton(new ImageIcon(rescaledImageMap1));
        map1bt.setBounds((int) screen.getWidth()/8, (int) screen.getHeight()/2, (int) screen.getWidth()/4, (int) screen.getHeight()/4);
        map1bt.setContentAreaFilled(false);
        map1bt.setBorderPainted(true);

        ImageIcon map2 = new ImageIcon("src/gameImages/GlenmouthMapImage.jpg");
        Image map2Image= map2.getImage();
        Image rescaledImageMap2 = map2Image.getScaledInstance((int) screen.getWidth()/4, (int) screen.getHeight()/4, Image.SCALE_SMOOTH);
        JRadioButton map2bt = new JRadioButton(new ImageIcon(rescaledImageMap2));
        map2bt.setBounds((int) screen.getWidth()/8 * 5, (int) (screen.getHeight()/2), (int) screen.getWidth()/4, (int) screen.getHeight()/4);
        map2bt.setContentAreaFilled(false);
        map2bt.setBorderPainted(true);


        mapChoices.add(map1bt);
        mapChoicePanel.add(map1bt);
        mapChoices.add(map2bt);
        mapChoicePanel.add(map2bt);

        map1bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map1bt.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
                map2bt.setBorder(null);
                mapLocation = "src/gameImages/FunnyLandMapImage.jpg";
            }
        });

        map2bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map2bt.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
                map1bt.setBorder(null);
                mapLocation = "src/gameImages/GlenmouthMapImage.jpg";
            }
        });

        JButton continueBt = new JButton("Continue");
        continueBt.setBounds((int) screen.getWidth()/2 - 100, (int)screen.getHeight()/6 * 5, 200, 80);
        continueBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapLocation != null) {
                    startController.getGameSetUpData().setMap(mapLocation);
                    startController.getDisplay().remove(mapChoicePanel);
                    startController.displayGameSetupPage();
                }
            }
        });
        mapChoicePanel.add(continueBt);

    }

    public void addMapChoicePage(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(mapChoicePanel);
        parent.repaint();
    }
}
