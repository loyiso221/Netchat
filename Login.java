import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private final JTextField username;
    private final JPasswordField password;

    public Login() {

        setTitle("Login System");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // MAIN PANEL
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        // ================= HEADER =================
        JLabel title = new JLabel("LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        panel.add(title, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        username = new JTextField(15);
        form.add(username, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        password = new JPasswordField(15);
        form.add(password, gbc);

        panel.add(form, BorderLayout.CENTER);

        // ================= BUTTONS =================
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(245, 245, 245));

        JButton loginBtn = new JButton("Login");
        JButton regBtn = new JButton("Register");

        styleButton(loginBtn);
        styleButton(regBtn);

        buttons.add(loginBtn);
        buttons.add(regBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        // ================= ACTIONS =================
        loginBtn.addActionListener(e -> login());

        regBtn.addActionListener(e -> {
            new Registration().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private void login() {

        String uname = username.getText();
        String pass = new String(password.getPassword());

        if (uname.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }

        boolean valid = FileManager.validateUser(uname, pass);

        JOptionPane.showMessageDialog(this,
                VALIDATION.loginMessage(valid, uname));

        if (valid) {
            new HOME(uname).setVisible(true);
            dispose(); // Close login window
        }
    }

    // BUTTON STYLE
    private void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(100, 35));
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
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
