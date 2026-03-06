package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    // Main frame
    private final ImagePanel startMenu;
    private final JButton startGame;
    private final JButton settings;
    private final JButton quit;


    public StartMenu() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        startMenu = new ImagePanel("src/Images/BattleNationsHomeScreen.png");
        startMenu.setLayout(null);
        //startMenu.setBackground(Color.RED); //Probably going to change this to an image.
        startMenu.setBounds(0, 0, screen.width, screen.height);

        startGame = new JButton("Start Game");
        startGame.setBounds(screen.width/2 - 100, screen.height/3, 200, 50);
        startMenu.add(startGame);

        settings = new JButton("Settings");
        settings.setBounds(screen.width/2 - 100, screen.height/2, 200, 50);
        startMenu.add(settings);

        quit = new JButton("Quit");
        quit.setBounds(screen.width/2 - 100, 2 * screen.height/3, 200, 50);
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        startMenu.add(quit);

    }

    public void addStartMenu(JFrame parent, ActionListener listener) {
        parent.add(startMenu);
        parent.setVisible(true);
        settings.addActionListener(listener);
    }

    public void removeFromParent(JFrame parent) {
        parent.remove(startMenu);
        parent.repaint();
    }

}