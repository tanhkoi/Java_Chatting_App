import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;

public class MessageFrame extends JLabel {
    private final JLabel messageLabel;

    public MessageFrame(String message) {
        messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        messageLabel.setBounds(50, 20, 100, 100);
        add(messageLabel, BorderLayout.CENTER);
    }
}
