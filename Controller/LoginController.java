package UAS.Controller;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

import UAS.View.MainMenu;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    JTextField username, password;

    public static final String FOTO_PATH = "D:\\Java\\NetBeansProject\\Programing\\Semester 3\\PBO\\UAS\\logo.png";

    public LoginController() {
        // JFrame
        JFrame jf = new JFrame("Login");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 300);

        // BufferedImage foto = ImageIO.read(new File(FOTO_PATH));
        // Image imgFoto = foto.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
        // JLabel jlLogo = new JLabel(new ImageIcon(imgFoto));
        // jlLogo.setBounds(5, 5, 64, 64);

        // JLabel
        JLabel jlUsername = new JLabel("Username");
        jlUsername.setBounds(70, 70, 150, 50);

        JLabel jlPassword = new JLabel("Password");
        jlPassword.setBounds(70, 100, 150, 50);

        JButton back = new JButton("Back");
        back.setBounds(200, 200, 70, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new MainMenu();
            }
        });

        // input
        username = new JTextField();
        username.setBounds(150, 85, 240, 20);

        password = new JTextField();
        password.setBounds(150, 115, 240, 20);

        JButton submit = new JButton("Submit");
        submit.setBounds(100, 200, 90, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseHandler conn = new DatabaseHandler();
                conn.connect();
                try {
                    java.sql.Statement stat = conn.con.createStatement();
                    ResultSet result = stat.executeQuery(
                            "SELECT * FROM users WHERE userName='" + username.getText() + "'AND password ='" + password.getText() + "'");
                    if (!result.next()) {
                        JOptionPane.showMessageDialog(null, "User not found.");
                    } else {
                        JOptionPane.showMessageDialog(null, "User found");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error occured when connecting to database.");
                }
                conn.disconnect();
            }
        });
        // jf.add(jlLogo);
        jf.add(jlUsername);
        jf.add(username);
        jf.add(jlPassword);
        jf.add(password);
        jf.add(submit);
        jf.add(back);

        jf.setLayout(null);
        jf.setVisible(true);
    }
}
