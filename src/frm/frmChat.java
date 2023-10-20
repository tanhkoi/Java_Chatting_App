package frm;

import java.io.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class frmChat extends javax.swing.JFrame {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private String username;

    public frmChat() {
        frmsignin signInForm = new frmsignin();
        signInForm.setVisible(true);

        signInForm.addLoginListener(new LoginListener() {
            @Override
            public void onLoginSuccess(String username) {
                signInForm.dispose();
                initializeChat(username);
            }
        });
    }

    private void initializeChat(String username) {
        initComponents();
        this.setVisible(true);
        this.username = username;
        connectToServer();
        lbUsername.setText(username);
        receiveMessages();
    }

    private void receiveMessages() {
        Thread messageReceiver = new Thread(() -> {
            try {
                while (true) {
                    String messageType = dis.readUTF();
                    if (messageType.equals("text")) {

                        String username = dis.readUTF();
                        String message = dis.readUTF();
                        chatbody.addItemLeft(message, username);
                        System.out.println("Received text: " + message);

                    } else if (messageType.equals("image")) {

                        String username = dis.readUTF();
                        int length = dis.readInt();
                        byte[] imageBytes = new byte[length];
                        dis.readFully(imageBytes);
                        ImageIcon imageIcon = new ImageIcon(imageBytes);
                        chatbody.addItemLeft("", username, imageIcon);
                        System.out.println("Received image: received_image.png");

                    } else if (messageType.equals("file")) {

                        String username = dis.readUTF();
                        String filename = dis.readUTF();
                        int length = dis.readInt();
                        byte[] fileBytes = new byte[length];
                        dis.readFully(fileBytes);
                        String formattedSize = formatFileSize(length);
                        chatbody.addItemFile("", username, filename, formattedSize);
                        System.out.println("Received file: " + filename);

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(frmChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        messageReceiver.start();
    }

    private void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 1234);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            taBox.requestFocus();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to the server. Exiting...");
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        btnFile = new javax.swing.JButton();
        btnImg = new javax.swing.JButton();
        btnVoice = new javax.swing.JButton();
        taBox = new javax.swing.JTextField();
        chatbody = new Conponents.Chat_body();
        btnSend = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        menu_Left1 = new Conponents.menu_Left();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client-Chat");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(java.awt.SystemColor.activeCaption);
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        btnFile.setBackground(new java.awt.Color(0, 0, 0));
        btnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/folder1x.png"))); // NOI18N
        btnFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        btnFile.setBorderPainted(false);
        btnFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFile.setFocusPainted(false);
        btnFile.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnFile.setMaximumSize(new java.awt.Dimension(25, 25));
        btnFile.setMinimumSize(new java.awt.Dimension(25, 25));
        btnFile.setPreferredSize(new java.awt.Dimension(25, 25));
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        btnImg.setBackground(new java.awt.Color(0, 0, 0));
        btnImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_image_white_24dp.png"))); // NOI18N
        btnImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImg.setBorderPainted(false);
        btnImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImg.setFocusPainted(false);
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });

        btnVoice.setBackground(new java.awt.Color(0, 0, 0));
        btnVoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/voice1x.png"))); // NOI18N
        btnVoice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVoice.setBorderPainted(false);
        btnVoice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVoice.setFocusPainted(false);

        taBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taBoxKeyPressed(evt);
            }
        });

        chatbody.setAutoscrolls(true);

        btnSend.setBackground(new java.awt.Color(0, 0, 0));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_send_white_24dp.png"))); // NOI18N
        btnSend.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnSend.setBorderPainted(false);
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.setFocusPainted(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 204, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Active");
        jLabel2.setAutoscrolls(true);
        jPanel4.add(jLabel2, java.awt.BorderLayout.CENTER);

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbUsername.setText("Username");
        jPanel4.add(lbUsername, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menu_Left1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoice, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(taBox)
                                .addGap(16, 16, 16)
                                .addComponent(btnSend))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chatbody, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                        .addGap(56, 56, 56))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(chatbody, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menu_Left1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImg, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btnVoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(taBox, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String message = taBox.getText().trim();
        if (!message.isEmpty()) {
            try {
                dos.writeUTF("text");
                dos.writeUTF(username);
                dos.writeUTF(message);
            } catch (Exception e) {
                // TODO: handle
            }
            taBox.setText("");
            taBox.requestFocus();
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void taBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taBoxKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSend.doClick();
        }
    }//GEN-LAST:event_taBoxKeyPressed

    private void btnImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImgActionPerformed
        try {
            JFileChooser myFileChooser = new JFileChooser();
            myFileChooser.showOpenDialog(chatbody);
            dos.writeUTF("image");
            dos.writeUTF(username);
            byte[] imageBytes = Files.readAllBytes(new File(myFileChooser.getSelectedFile().getAbsolutePath()).toPath());
            dos.writeInt(imageBytes.length);
            dos.write(imageBytes);
        } catch (Exception e) {
            // TODO: handle
        }
    }//GEN-LAST:event_btnImgActionPerformed

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        try {
            JFileChooser myFileChooser = new JFileChooser();
            myFileChooser.showOpenDialog(chatbody);
            File selectedFile = myFileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            dos.writeUTF("file");
            dos.writeUTF(username);
            dos.writeUTF(fileName);
            byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
            dos.writeInt(fileBytes.length);
            dos.write(fileBytes);
        } catch (Exception e) {
            // TODO: handle
        }
    }//GEN-LAST:event_btnFileActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            frmChat frm = new frmChat();
        });
    }

    public static String formatFileSize(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            return sizeInBytes + " B";
        } else if (sizeInBytes < 1024 * 1024) {
            return String.format("%.2f KB", (double) sizeInBytes / 1024);
        } else if (sizeInBytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", (double) sizeInBytes / (1024 * 1024));
        } else {
            return String.format("%.2f GB", (double) sizeInBytes / (1024 * 1024 * 1024));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnVoice;
    private Conponents.Chat_body chatbody;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbUsername;
    private Conponents.menu_Left menu_Left1;
    private javax.swing.JTextField taBox;
    // End of variables declaration//GEN-END:variables

}
