package frm;

import DB.MongoDBAccess;

public class frmsignup extends javax.swing.JFrame {

    public frmsignup() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtPasswordUp = new javax.swing.JTextField();
        bttDangKy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbThongbao = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng Ký");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 159, 47));

        txtUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsernameMouseClicked(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 341, 34));

        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmailMouseClicked(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 341, 34));

        txtPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordMouseClicked(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 341, 34));

        txtPasswordUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPasswordUpMouseClicked(evt);
            }
        });
        getContentPane().add(txtPasswordUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 341, 34));

        bttDangKy.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bttDangKy.setText("Đăng ký");
        bttDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttDangKyActionPerformed(evt);
            }
        });
        getContentPane().add(bttDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 130, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên đăng nhập");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mật khẩu");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nhập lại mật khẩu");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        lbThongbao.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lbThongbao.setForeground(java.awt.Color.red);
        getContentPane().add(lbThongbao, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/nentoi.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsernameMouseClicked
        txtUsername.setText("");
    }//GEN-LAST:event_txtUsernameMouseClicked

    private void txtEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMouseClicked
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailMouseClicked

    private void txtPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordMouseClicked
        txtPassword.setText("");
    }//GEN-LAST:event_txtPasswordMouseClicked

    private void txtPasswordUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPasswordUpMouseClicked
        txtPasswordUp.setText("");
    }//GEN-LAST:event_txtPasswordUpMouseClicked

    private void bttDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttDangKyActionPerformed

        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtPasswordUp.getText();

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (username.isEmpty() || !email.matches(emailRegex) || password.isEmpty() || confirmPassword.isEmpty()) {
            lbThongbao.setText("Vui long nhap day du va chinh xac thong tin!");
        } else if (!password.equals(confirmPassword)) {
            lbThongbao.setText("Nhap lai mat khau!");
        } else {
            MongoDBAccess db = new MongoDBAccess();
            int success = db.Register(username, email, password);
            if (success == -1) {
                lbThongbao.setText("Username da ton tai");
            } else if (success == 1) {
                this.dispose();

            }
        }
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
            java.util.logging.Logger.getLogger(frmsignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmsignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmsignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmsignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmsignup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttDangKy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbThongbao;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordUp;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
