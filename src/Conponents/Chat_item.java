package Conponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class Chat_item extends javax.swing.JPanel {

    private JLabel label;

    public Chat_item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);

    }

    public void setUserProfile(String user) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton cmd = new JButton(user);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30, 121, 213));
        cmd.setFont(new java.awt.Font("sansserif", 1, 13));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        layer.add(cmd);
        add(layer, 0);
    }

    public void setText(String text) {
        txt.setText(text);
    }

    public void setTime(String time) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(time);
        label.setForeground(new Color(110, 110, 110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }

    public void setImage(boolean right, Icon... image) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        Chat_img chatImage = new Chat_img(right);
        chatImage.addImage(image);
        layer.add(chatImage);
        add(layer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new swing.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
