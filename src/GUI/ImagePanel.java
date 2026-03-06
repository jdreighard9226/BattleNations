package GUI;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel{

    private final Image backgroundImage;

    public ImagePanel(String path) {
        backgroundImage = new ImageIcon(path).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
