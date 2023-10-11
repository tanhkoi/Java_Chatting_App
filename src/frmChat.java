
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.border.BevelBorder;

public class frmChat extends javax.swing.JFrame {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final String username;
    private int labelY = 0;

    public frmChat() {
        initComponents();
        username = JOptionPane.showInputDialog("Nhap nickname:");
        if (username == null || username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nickname khong duoc de trong...");
            System.exit(0);
        }
        connectToServer();
        setTitle("Chat Client - " + username);
        receiveMessages();
    }

    private JLabel messFrame(String text) {
        JLabel label = new JLabel(text);
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int width = metrics.stringWidth(label.getText());
        int height = metrics.getHeight();
        label.setPreferredSize(new Dimension(width, height));
        label.setBounds(10, labelY, width, height);
        labelY += 20;
        return label;
    }

    private void receiveMessages() {
        Thread messageReceiver = new Thread(() -> {
            try {
                String message;

                while ((message = in.readLine()) != null) {

                    panelTextBox.add(messFrame(message));
                    panelTextBox.revalidate();
                    panelTextBox.repaint();

                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Connection to server lost. Exiting...");
                System.exit(1);
            }
        });
        messageReceiver.start();
    }

    private void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(username);
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
        taBox = new javax.swing.JTextField();
        btnVoice = new javax.swing.JButton();
        btnFile = new javax.swing.JButton();
        btnImg = new javax.swing.JButton();
        panelOnline = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelTextBox = new javax.swing.JPanel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client-Chat");

        btnSend.setBackground(new java.awt.Color(153, 153, 153));
        btnSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_send_white_24dp.png"))); // NOI18N
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        taBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnVoice.setBackground(new java.awt.Color(0, 0, 0));
        btnVoice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/voice1x.png"))); // NOI18N
        btnVoice.setEnabled(false);

        btnFile.setBackground(new java.awt.Color(0, 0, 0));
        btnFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/folder1x.png"))); // NOI18N
        btnFile.setEnabled(false);

        btnImg.setBackground(new java.awt.Color(0, 0, 0));
        btnImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/baseline_image_white_24dp.png"))); // NOI18N
        btnImg.setEnabled(false);

        panelOnline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelOnlineLayout = new javax.swing.GroupLayout(panelOnline);
        panelOnline.setLayout(panelOnlineLayout);
        panelOnlineLayout.setHorizontalGroup(
            panelOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelOnlineLayout.setVerticalGroup(
            panelOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        jLabel1.setText("Online");

        panelTextBox.setBackground(new java.awt.Color(255, 255, 255));
        panelTextBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelTextBoxLayout = new javax.swing.GroupLayout(panelTextBox);
        panelTextBox.setLayout(panelTextBoxLayout);
        panelTextBoxLayout.setHorizontalGroup(
            panelTextBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        panelTextBoxLayout.setVerticalGroup(
            panelTextBoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(taBox, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSend))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btnFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVoice))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(68, 68, 68)
                                .addComponent(panelTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(389, 389, 389))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFile, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImg, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(taBox, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        String message = taBox.getText().trim();
        if (!message.isEmpty()) {
            out.println(message);
            taBox.setText("");
            taBox.requestFocus();
        }
    }//GEN-LAST:event_btnSendActionPerformed

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
            new frmChat().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFile;
    private javax.swing.JButton btnImg;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnVoice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel panelOnline;
    private javax.swing.JPanel panelTextBox;
    private javax.swing.JTextField taBox;
    // End of variables declaration//GEN-END:variables

}
