import javax.swing.*;
import java.awt.*;

public class HOME extends JFrame {


    public HOME(String username) {

        setTitle("QuickChat Home");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240,240,240));

        // HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(33,150,243));

        JLabel title = new JLabel("Welcome to QuickChat");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(title);

        // CENTER
        JPanel center = new JPanel(new GridLayout(3,1,10,10));
        center.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        center.setBackground(new Color(240,240,240));

        JLabel welcome = new JLabel("Welcome, " + username);
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton sendBtn = new JButton("Send Messages");
        JButton recentBtn = new JButton("Show Recently Sent");
        JButton quitBtn = new JButton("Quit");

        styleButton(sendBtn);
        styleButton(recentBtn);
        styleButton(quitBtn);

        center.add(sendBtn);
        center.add(recentBtn);
        center.add(quitBtn);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(welcome, BorderLayout.CENTER);
        mainPanel.add(center, BorderLayout.SOUTH);

        add(mainPanel);

        // SEND MESSAGE
        sendBtn.addActionListener(e -> sendMessages());

        // RECENT MESSAGES
        recentBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Coming Soon.")
        );

        // QUIT
        quitBtn.addActionListener(e -> System.exit(0));
    }

    // SEND MESSAGES METHOD
    private void sendMessages() {

        String input = JOptionPane.showInputDialog(
                this,
                "How many messages do you want to send?"
        );

        int total;

        try {

            total = Integer.parseInt(input);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Invalid number.");

            return;
        }

        for (int i = 0; i < total; i++) {

            String recipient = JOptionPane.showInputDialog(
                    this,
                    "Enter recipient number:"
            );

            String text = JOptionPane.showInputDialog(
                    this,
                    "Enter your message:"
            );

            Message msg = new Message(
                    i + 1,
                    recipient,
                    text
            );

            // MESSAGE LENGTH
            if (!msg.validMessageLength()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a message of less than 250 characters.");

                i--;
                continue;
            }

            // RECIPIENT VALIDATION
            if (!msg.checkRecipientCell()) {

                JOptionPane.showMessageDialog(this,
                        "Cell phone number incorrectly formatted or does not contain international code.");

                i--;
                continue;
            }

            // OPTIONS
            String[] options = {
                    "Send Message",
                    "Disregard Message",
                    "Store Message"
            };

            int choice = JOptionPane.showOptionDialog(
                    this,
                    "Choose an option",
                    "QuickChat",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {

                case 0 -> {
                    MessageStore.storeMessage(msg);

                    JOptionPane.showMessageDialog(this,
                            "Message successfully sent\n\n"
                                    + msg.printMessages());
                }

                case 1 -> JOptionPane.showMessageDialog(this,
                            "Message disregarded.");

                case 2 -> {
                    MessageStore.storeMessage(msg);

                    JOptionPane.showMessageDialog(this,
                            "Message successfully stored.");
                }
            }
        }

        JOptionPane.showMessageDialog(this,
                "Total messages sent: "
                        + Message.returnTotalMessages());
    }

    // BUTTON STYLE
    private void styleButton(JButton button) {

        button.setFocusPainted(false);
        button.setBackground(new Color(33,150,243));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(150,40));
    }
    // </editor-fold>
    // Variables declaration - do not modify
    // End of variables declaration


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

   

    // Variables declaration - do not modify                     
    // End of variables declaration                   

}
