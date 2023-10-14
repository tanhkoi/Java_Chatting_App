package Conponents;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

public class CustomLabelBorder implements Border {

    private Color borderColor;

    public CustomLabelBorder(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(borderColor);
        g.drawRect(x, y, width - 1, height - 1);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1); // Adjust these values as needed
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
