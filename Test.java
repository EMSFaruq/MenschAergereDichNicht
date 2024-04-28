import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Test {

    int rows = 3;
    int cols = 3;
    int width = 800;
    int height = 600;
    int IconWidth = width / rows;
    int IconHeight = height / cols;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test test = new Test();
            test.FrameUI();
        });
    }

    void FrameUI() {
        JFrame frame = new JFrame("Test Layouts");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(IconWidth * rows, IconHeight * cols));
        GridLayout gLayout = new GridLayout(rows, cols);
        frame.setLayout(gLayout);
        frame.setUndecorated(false);
        frame.setVisible(true);
        addGrids(frame);
    }

    void addGrids(JFrame frame) {
        int r = 100,
                g = 50,
                b = 50;
        int StrokeSize = 5;

        JLabel[] label = new JLabel[rows * cols];
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setText(i + "");
            label[i].setIcon(drawIcon(IconWidth, IconHeight, r, g, b, StrokeSize));
            label[i].setFont(new Font("Sans", Font.BOLD, 29));
            frame.add(label[i]);
        }
    }

    ImageIcon drawIcon(int width, int height, int r, int g, int b, int StrokeSize) {
        Image image = new BufferedImage(width, height, g);
        Graphics gr = image.getGraphics();
        Graphics2D g2d = (Graphics2D) gr;
        g2d.setColor(new Color(100, 50, 50));
        g2d.fillRect(0, 0, width - StrokeSize / 2, height - StrokeSize / 2);
        g2d.setColor(new Color(0, 0, 0));
        g2d.setStroke(new BasicStroke(StrokeSize));
        g2d.drawRect(StrokeSize / 2, StrokeSize / 2, width, height);

        return new ImageIcon(image);
    }
}
