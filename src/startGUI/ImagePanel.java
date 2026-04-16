package startGUI;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that allows for displaying a background image.
 *
 * <p>This panel allows for a background image to be added to the Panel. The class automatically resizes the image
 * to fit the entirety of the panel.</p>
 *
 */
public class ImagePanel extends JPanel {

    /** The image that will be drawn as the background image. */
    private final Image backgroundImage;

    /**
     * Constructs an image panel that uses the given file string as the image location that will be used for the
     * background.
     *
     * @param path the file path to the image to display
     */
    public ImagePanel(String path) {
        backgroundImage = new ImageIcon(path).getImage();
    }

    /** Overrides the paint component. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

}
