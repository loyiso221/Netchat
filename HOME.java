import javax.swing.*;
import java.awt.*;

public class HOME extends JFrame {

    public HOME(String username) {

        setTitle("Home Dashboard");
        setSize(500, 300);
        setLocationRelativeTo(null); // Center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBackground(new Color(33, 150, 243)); // Blue
        header.setPreferredSize(new Dimension(500, 60));

        JLabel title = new JLabel("HOME PAGE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        header.add(title);

        // ================= CENTER =================
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(new Color(240, 240, 240));

        JLabel welcome = new JLabel("Welcome, " + username + " 👋");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("Select an option below:");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(welcome);
        centerPanel.add(subtitle);

        // ================= FOOTER (BUTTONS) =================
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));

        JButton profileBtn = new JButton("Profile");
        JButton logoutBtn = new JButton("Logout");

        styleButton(profileBtn);
        styleButton(logoutBtn);

        footer.add(profileBtn);
        footer.add(logoutBtn);

        // ================= ACTIONS =================
        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out!");
            dispose();
            new Login().setVisible(true); // Make sure LOGIN class exists
        });

        profileBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Profile clicked!");
        });

        // ================= ADD COMPONENTS =================
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Button Styling Method
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 35));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
