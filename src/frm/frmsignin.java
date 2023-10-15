package frm;

import DB.MongoDBAccess;

public class frmsignin extends javax.swing.JFrame {

    private LoginListener loginListener;

    public frmsignin() {
        initComponents();
    }

    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener;
    }

    private void notifyLoginSuccess(String username) {
        if (loginListener != null) {
            loginListener.onLoginSuccess(username);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        bttDangKy = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bttDangNhap = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 130, 31));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 113, 31));

        jLabel4.setFont(new java.awt.Font("StarsStripes", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 204));
        jLabel4.setText("Chat Room");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 260, 80));

        txtPassword.setToolTipText("");
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 200, 30));
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 200, 30));

        bttDangKy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttDangKy.setText("Đăng ký");
        bttDangKy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttDangKyActionPerformed(evt);
            }
        });
        getContentPane().add(bttDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Giúp bạn kết nối ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 300, 50));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Và chia sẻ đến mọi người");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 330, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("quên mật khẩu ?");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 130, 40));

        bttDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bttDangNhap.setText("Đăng nhập");
        bttDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bttDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttDangNhapActionPerformed(evt);
            }
        });
        getContentPane().add(bttDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 110, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nentoi.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttDangNhapActionPerformed
        MongoDBAccess mgDB = new MongoDBAccess();
        char[] password = txtPassword.getPassword();
        int loginCode = mgDB.Login(txtUsername.getText(), new String(password));
        if (loginCode == 1) {
            // TODO: dang nhap thanh cong
            notifyLoginSuccess(txtUsername.getText());
        } else if (loginCode == -1) {
            // TODO: sai mat khau
        } else if (loginCode == -2) {
            // TODO: sai uername
        }
    }//GEN-LAST:event_bttDangNhapActionPerformed

    private void bttDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttDangKyActionPerformed
        frmsignup frmsu = new frmsignup();
        frmsu.setVisible(true);
    }//GEN-LAST:event_bttDangKyActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmsignin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmsignin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmsignin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmsignin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmsignin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttDangKy;
    private javax.swing.JButton bttDangNhap;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
