import javax.swing.*;
import java.awt.*;

public class Registration extends JFrame {

    private final JTextField username;
    private final JTextField phone;
    private JPasswordField password;
    private JPasswordField repassword;

    public Registration() {

        setTitle("Register");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));

        // ========== HEADER ==========
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);

        // ========== FORM ==========
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        form.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        username = new JTextField(15);
        form.add(username, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = 1;
        form.add(new JLabel("Cellphone:"), gbc);

        gbc.gridx = 1;
        phone = new JTextField(15);
        form.add(phone, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        form.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        password = new JPasswordField(15);
        form.add(password, gbc);

        // Re-enter Password
        gbc.gridx = 0; gbc.gridy = 3;
        form.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        repassword = new JPasswordField(15);
        form.add(repassword, gbc);

        // Show Password Checkbox
        gbc.gridx = 1; gbc.gridy = 4;
        JCheckBox showPass = new JCheckBox("Show Passwords");
        showPass.setBackground(new Color(245, 245, 245));
        form.add(showPass, gbc);

        showPass.addActionListener(e -> {
            char echo = showPass.isSelected() ? (char) 0 : '•';
            password.setEchoChar(echo);
            repassword.setEchoChar(echo);
        });

        panel.add(form, BorderLayout.CENTER);

        // ========== BUTTONS ==========
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(245, 245, 245));

        JButton regBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        styleButton(regBtn);
        styleButton(backBtn);

        buttons.add(backBtn);
        buttons.add(regBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        // ========== ACTIONS ==========
        regBtn.addActionListener(e -> register());

        backBtn.addActionListener(e -> {
            new Login().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private void register() {

        String uname = username.getText();
        String pass = new String(password.getPassword());
        String repass = new String(repassword.getPassword());
        String ph = phone.getText();

        if (!VALIDATION.checkUsername(uname)) {
            JOptionPane.showMessageDialog(this, VALIDATION.usernameMessage(uname));
            return;
        }

        if (!VALIDATION.checkPassword(pass)) {
            JOptionPane.showMessageDialog(this, VALIDATION.passwordMessage(pass));
            return;
        }

        if (!pass.equals(repass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match");
            return;
        }

        if (!VALIDATION.checkCellphone(ph)) {
            JOptionPane.showMessageDialog(this, VALIDATION.cellphoneMessage(ph));
            return;
        }

        FileManager.saveUser(new User(uname, pass));

        JOptionPane.showMessageDialog(this, "Registration successful!");

        new Login().setVisible(true);
        dispose();
    }

    // BUTTON STYLE
    private void styleButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(76, 175, 80)); // Green
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(100, 35));
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
