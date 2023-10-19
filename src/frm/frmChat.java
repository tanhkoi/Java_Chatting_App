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
        setTitle("Chat Client - " + username);
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
        btnSend = new javax.swing.JButton();
        btnVoice = new javax.swing.JButton();
        btnFile = new javax.swing.JButton();
        btnImg = new javax.swing.JButton();
        panelOnline = new javax.swing.JPanel();
        taBox = new javax.swing.JTextField();
        chatbody = new Conponents.Chat_body();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client-Chat");
        setPreferredSize(new java.awt.Dimension(1000, 700));

        btnSend.setBackground(new java.awt.Color(153, 153, 153));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_send_white_24dp.png"))); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnVoice.setBackground(new java.awt.Color(0, 0, 0));
        btnVoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/voice1x.png"))); // NOI18N
        btnVoice.setEnabled(false);

        btnFile.setBackground(new java.awt.Color(0, 0, 0));
        btnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/folder1x.png"))); // NOI18N
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });

        btnImg.setBackground(new java.awt.Color(0, 0, 0));
        btnImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_image_white_24dp.png"))); // NOI18N
        btnImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImgActionPerformed(evt);
            }
        });

        panelOnline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelOnlineLayout = new javax.swing.GroupLayout(panelOnline);
        panelOnline.setLayout(panelOnlineLayout);
        panelOnlineLayout.setHorizontalGroup(
            panelOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        panelOnlineLayout.setVerticalGroup(
            panelOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        taBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taBoxKeyPressed(evt);
            }
        });

        chatbody.setAutoscrolls(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFile)
                                .addGap(18, 18, 18)
                                .addComponent(btnImg)
                                .addGap(18, 18, 18)
                                .addComponent(btnVoice)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(taBox))
                        .addGap(18, 18, 18)
                        .addComponent(btnSend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chatbody, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chatbody, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoice, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taBox, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        pack();
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
            dos.writeUTF("file");
            dos.writeUTF(username);
            byte[] fileBytes = Files.readAllBytes(new File(myFileChooser.getSelectedFile().getAbsolutePath()).toPath());
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelOnline;
    private javax.swing.JTextField taBox;
    // End of variables declaration//GEN-END:variables

}
