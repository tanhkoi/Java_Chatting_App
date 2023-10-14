package Conponents;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;

public class Chat_left_with_profile extends javax.swing.JLayeredPane {

    public Chat_left_with_profile() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }

    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    public void setText(String text) {
        txt.setText(text);
    }

    public void setTime() {
        DateFormat date_format_obj = new SimpleDateFormat("HH:mm");
        Date date_obj = new Date();
        txt.setTime(date_format_obj.format(date_obj));
    }
    
    public void setImage(Icon... image) {
        txt.setImage(false, image);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new Conponents.Chat_item();

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Conponents.Chat_item txt;
    // End of variables declaration//GEN-END:variables
}
