import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HOME extends JFrame {

    private final String currentUser;

    public HOME(String username) {

        this.currentUser = username;

        setTitle("QuickChat Home");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(33, 150, 243));

        JLabel title = new JLabel("Welcome To QuickChat");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        header.add(title);

        // WELCOME LABEL
        JLabel welcome = new JLabel("Welcome, " + username, SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton sendBtn = new JButton("Send Messages");
        JButton recentBtn = new JButton("Show Recently Sent");
        JButton storedBtn = new JButton("Stored Messages");
        JButton quitBtn = new JButton("Quit");

        styleButton(sendBtn);
        styleButton(recentBtn);
        styleButton(storedBtn);
        styleButton(quitBtn);

        buttonPanel.add(sendBtn);
        buttonPanel.add(recentBtn);
        buttonPanel.add(storedBtn);
        buttonPanel.add(quitBtn);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(welcome, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ACTIONS
        sendBtn.addActionListener(e -> sendMessages());
        recentBtn.addActionListener(e -> showRecentMessages());
        storedBtn.addActionListener(e -> storedMessagesMenu());
        quitBtn.addActionListener(e -> System.exit(0));
    }

    // ================= SEND MESSAGES =================
    private void sendMessages() {

        String input = JOptionPane.showInputDialog(this,
                "How many messages do you want to send?");

        if (input == null) return;

        int total;

        try {
            total = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number.");
            return;
        }

        for (int i = 0; i < total; i++) {

            String recipient = JOptionPane.showInputDialog(this, "Enter recipient number:");
            if (recipient == null) return;

            String text = JOptionPane.showInputDialog(this, "Enter your message:");
            if (text == null) return;

            Message msg = new Message(i + 1, recipient, text);

            if (!msg.validMessageLength()) {
                JOptionPane.showMessageDialog(this, "Message exceeds 250 characters.");
                i--;
                continue;
            }

            if (!msg.checkRecipientCell()) {
                JOptionPane.showMessageDialog(this, "Invalid cell number format.");
                i--;
                continue;
            }

            String[] options = {"Send Message", "Disregard Message", "Store Message"};

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
                    MessageStore.storeMessage(msg, currentUser);
                    JOptionPane.showMessageDialog(this,
                            "Message Sent\n\n" + msg.printMessages());
                }

                case 1 -> {
                    MessageStore.disregardedMessages.add(msg.getMessageText());
                    JOptionPane.showMessageDialog(this, "Message Disregarded");
                }

                case 2 -> {
                    MessageStore.storeMessage(msg, currentUser);
                    JOptionPane.showMessageDialog(this, "Message Stored Successfully");
                }
            }
        }

        JOptionPane.showMessageDialog(this,
                "Total Messages Sent: " + Message.returnTotalMessages());
    }

    // ================= RECENT MESSAGES =================
    private void showRecentMessages() {

        if (MessageStore.sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No messages available.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (String msg : MessageStore.sentMessages) {
            sb.append(msg).append("\n\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    // ================= STORED MENU =================
    private void storedMessagesMenu() {

        MessageStore.loadMessages();

        String[] options = {
                "Display Sender & Recipient",
                "Longest Message",
                "Search Message ID",
                "Search Recipient",
                "Delete Message",
                "Full Report"
        };

        int choice = JOptionPane.showOptionDialog(
                this,
                "Stored Messages",
                "QuickChat",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0 -> displaySenders();
            case 1 -> displayLongestMessage();
            case 2 -> searchMessageID();
            case 3 -> searchRecipient();
            case 4 -> deleteMessage();
            case 5 -> fullReport();
        }
    }

    private void displaySenders() {

        StringBuilder sb = new StringBuilder();

        for (StoredMessage msg : MessageStore.storedMessages) {
            sb.append("Sender: ").append(msg.getSender())
              .append("\nRecipient: ").append(msg.getRecipient())
              .append("\n\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void displayLongestMessage() {
        JOptionPane.showMessageDialog(this, MessageStore.getLongestMessage());
    }

    private void searchMessageID() {

        String id = JOptionPane.showInputDialog(this, "Enter Message ID");
        if (id == null) return;

        StoredMessage msg = MessageStore.searchByID(id);

        if (msg != null) {
            JOptionPane.showMessageDialog(this,
                    "Recipient: " + msg.getRecipient() +
                    "\nMessage: " + msg.getMessage());
        } else {
            JOptionPane.showMessageDialog(this, "Message Not Found");
        }
    }

    private void searchRecipient() {

        String recipient = JOptionPane.showInputDialog(this, "Enter Recipient");
        if (recipient == null) return;

        ArrayList<StoredMessage> results =
                MessageStore.searchByRecipient(recipient);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No messages found.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (StoredMessage msg : results) {
            sb.append(msg.getMessage()).append("\n\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void deleteMessage() {

        String hash = JOptionPane.showInputDialog(this, "Enter Message Hash");
        if (hash == null) return;

        boolean deleted = MessageStore.deleteByHash(hash);

        JOptionPane.showMessageDialog(this,
                deleted ? "Message Deleted Successfully" : "Message Not Found");
    }

    private void fullReport() {
        JOptionPane.showMessageDialog(this, MessageStore.generateReport());
    }

    // ================= STYLE =================
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
    }


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
