import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserLogin extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    JCheckBox rememberMe;
    JCheckBox notifications;

    JButton loginButton;

    public UserLogin() {

        setTitle("User Login System");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username
        add(new JLabel("Username:"));

        usernameField = new JTextField();
        add(usernameField);

        // Password
        add(new JLabel("Password:"));

        passwordField = new JPasswordField();
        add(passwordField);

        // Remember Me
        add(new JLabel("Preferences:"));

        JPanel preferencePanel = new JPanel();

        rememberMe = new JCheckBox("Remember Me");
        notifications = new JCheckBox("Receive Notifications");

        preferencePanel.add(rememberMe);
        preferencePanel.add(notifications);

        add(preferencePanel);

        // Login Button
        loginButton = new JButton("Login");

        add(new JLabel(""));
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        UserLogin.this,
                        "Please enter username and password.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                    );

                } else {

                    String message =
                        "Login Successful!\n" +
                        "Welcome, " + username +
                        "\nRemember Me: " +
                        (rememberMe.isSelected() ? "Yes" : "No") +
                        "\nNotifications: " +
                        (notifications.isSelected() ? "Enabled" : "Disabled");

                    JOptionPane.showMessageDialog(
                        UserLogin.this,
                        message,
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserLogin();
    }
}