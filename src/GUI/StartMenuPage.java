package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuPage {
    // Main frame
    private final ImagePanel startMenuPage;
    private JFrame parent;
    private StartController startController;


    public StartMenuPage() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        startMenuPage = new ImagePanel("src/Images/BattleNationsHomeScreen.png");
        startMenuPage.setLayout(null);
        //startMenu.setBackground(Color.RED); //Probably going to change this to an image.
        startMenuPage.setBounds(0, 0, screen.width, screen.height);

        JButton startGame = new JButton("Start Game");
        startGame.setBounds(screen.width/2 - 100, screen.height/3, 200, 50);
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.remove(startMenuPage);
                startController.displayMapDisplay();
            }
        });
        startMenuPage.add(startGame);

        JButton settings = new JButton("Settings");
        settings.setBounds(screen.width/2 - 100, screen.height/2, 200, 50);
        settings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.remove(startMenuPage);
                startController.displaySettingsPage();
            }
        });
        startMenuPage.add(settings);

        JButton quit = new JButton("Quit");
        quit.setBounds(screen.width/2 - 100, 2 * screen.height/3, 200, 50);
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        startMenuPage.add(quit);

    }

    public void addStartMenuPage(StartController startController) {
        this.startController = startController;
        this.parent = startController.getDisplay();
        parent.add(startMenuPage);
        parent.repaint();
    }


}