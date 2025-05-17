package com.example.statscroll.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("StatScroll - Login");
        setSize(700, 500); // Più grande
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colori fantasy
        Color parchmentColor = new Color(245, 222, 179);
        Color borderColor = new Color(139, 69, 19);
        Color fieldColor = new Color(255, 248, 220);
        Color buttonColor = new Color(210, 180, 140);

        // Titolo
        JLabel titleLabel = new JLabel("StatScroll", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 70));
        titleLabel.setForeground(new Color(49, 22, 6));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Pannello login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(parchmentColor);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = createRoundedField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = createRoundedPasswordField();
        styleField(passField);

        JButton loginButton = createRoundedButton("Login");

        styleLabel(userLabel);
        styleLabel(passLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passLabel, gbc);

        gbc.gridx = 1;
        loginPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        add(loginPanel, BorderLayout.CENTER);

        // Bottone "Sign In"
        JButton signInButton = createRoundedButton("Sign In");
        JPanel signInPanel = new JPanel();
        signInPanel.setBackground(parchmentColor);
        signInPanel.add(signInButton);
        add(signInPanel, BorderLayout.SOUTH);

        // Azioni
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            JOptionPane.showMessageDialog(this, "Login di: " + username);
        });

        signInButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Vai alla pagina di registrazione...");
        });

        // Sfondo finestra
        getContentPane().setBackground(parchmentColor);

        setVisible(true);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Serif", Font.PLAIN, 22));
        label.setForeground(new Color(80, 40, 20));
    }

    private void styleField(JTextField field) {
        field.setBackground(new Color(255, 248, 220));
        field.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 1));
        field.setFont(new Font("Serif", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(250, 35));
    }

    private JTextField createRoundedField() {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    int arc = 20;
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                int arc = 20;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(139, 69, 19));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };
        field.setOpaque(false);
        field.setBackground(new Color(255, 248, 220));
        styleField(field);
        return field;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                int arc = 30;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                int arc = 30;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(139, 69, 19));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBackground(new Color(210, 180, 140));
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(200, 45));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage());
    }
    private JPasswordField createRoundedPasswordField() {
        JPasswordField field = new JPasswordField() {
            @Override
            protected void paintComponent(Graphics g) {
                int arc = 20;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
                g2.dispose();
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                int arc = 20;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(139, 69, 19)); // stesso colore usato per altri bordi
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
                g2.dispose();
            }
        };
        field.setOpaque(false);
        field.setBackground(new Color(255, 248, 220)); // stesso colore pergamena
        field.setFont(new Font("Serif", Font.PLAIN, 18));
        field.setPreferredSize(new Dimension(250, 35));
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding interno
        return field;
    }
}
