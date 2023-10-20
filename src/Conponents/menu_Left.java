package Conponents;

import net.miginfocom.swing.MigLayout;

public class menu_Left extends javax.swing.JPanel {

    public menu_Left() {
        initComponents();
        init();
    }

    private void init() {
        menuUser.setLayout(new MigLayout());
        showPeople();
    }

    private void showPeople() {
        for (int i = 0; i < 4; i++) {
            menuUser.add(new item_people("People " + i), "wrap");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        menuUser = new javax.swing.JLayeredPane();

        setBackground(java.awt.SystemColor.activeCaption);
        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Online");

        menuUser.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));

        javax.swing.GroupLayout menuUserLayout = new javax.swing.GroupLayout(menuUser);
        menuUser.setLayout(menuUserLayout);
        menuUserLayout.setHorizontalGroup(
            menuUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuUserLayout.setVerticalGroup(
            menuUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuUser)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(menuUser))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane menuUser;
    // End of variables declaration//GEN-END:variables
}
