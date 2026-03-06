package GUI;

import javax.swing.*;

public class Controler {
    final JFrame display;

    public Controler() {
        display = new JFrame();
        display.setExtendedState(JFrame.MAXIMIZED_BOTH);
        display.setUndecorated(true);
        displayStartMenu();
    }

    public void displayStartMenu() {
        StartMenu myStart = new StartMenu(display);
    }

    public static void main(String[] args) {
         //Might want to use this later so we have full control over how everything looks
        Controler myControler = new Controler();
    }
}
