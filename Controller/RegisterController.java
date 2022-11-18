package UAS.Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

import UAS.Model.CategoryUser;
import UAS.Model.User;
import UAS.View.MainMenu;

public class RegisterController {
    User user;
    JTextField username, password, email, followers;
    JRadioButton pria, wanita;
    JComboBox kategori;

    public RegisterController() {
        user = new User();
        form();
    }

    public void insertDB() {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            PreparedStatement stat = conn.con.prepareStatement(
                    "INSERT INTO users(userName, password, userEmail, userGender, userFollowers) VALUES(?,?,?,?,?)");
            stat.setString(1, user.getUserName());
            stat.setString(2, user.getPassword());
            stat.setString(3, user.getUserEmail());
            stat.setString(4, user.getUserGender());
            stat.setInt(5, user.getUserFollowers());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil memasukkan data ke database");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error!! Gagal memasukkan data ke database");
            System.out.println(e);
        }
        conn.disconnect();
    }

    public void form() {
        // JFrame
        JFrame jf = new JFrame("Input Data");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 800);

        JLabel jlNama = new JLabel("Username");
        jlNama.setBounds(50, 60, 150, 50);

        JLabel jlPassword = new JLabel("User Password");
        jlPassword.setBounds(50, 90, 150, 30);

        JLabel jlEmail = new JLabel("Email");
        jlEmail.setBounds(50, 120, 150, 50);

        JLabel jlGender = new JLabel("Jenis Kelamin");
        jlGender.setBounds(50, 150, 150, 30);

        JLabel jlCategory = new JLabel("Category Akun");
        jlCategory.setBounds(50, 180, 150, 30);

        JLabel jlFollowers = new JLabel("Followers");
        jlFollowers.setBounds(50, 210, 150, 30);

        //input
        username = new JTextField();
        username.setBounds(180, 35, 240, 20);

        password = new JTextField();
        password.setBounds(180, 65, 240, 20);

        email = new JTextField();
        email.setBounds(180, 95, 240, 20);

        pria = new JRadioButton("Pria");
        pria.setBounds(180, 155, 120, 20);
        wanita = new JRadioButton("Wanita");
        wanita.setBounds(300, 155, 120, 20);
        ButtonGroup grupJK = new ButtonGroup();
        grupJK.add(pria);
        grupJK.add(wanita);

        String[] listCategory = { "1. Private", "2. Creator", "3. Business" };
        kategori = new JComboBox(listCategory);
        kategori.setBounds(180, 335, 240, 20);

        CategoryUser temp = new CategoryUser();
        
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM categoryuser WHERE categoryId='" + kategori.getSelectedIndex()+1 + "'");
            if (result.next()) {
                int idkategori = result.getInt("categoryId");
                String namakategori = result.getString("categoryName");
                temp.setCategoryId(idkategori);
                temp.setCategoryName(namakategori);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error occured when connecting to database.");
        }
        conn.disconnect(); 

        followers = new JTextField();
        followers.setBounds(180, 575, 240, 20);

        JButton submit = new JButton("Registrasi");
        submit.setBounds(100, 700, 90, 30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData(temp);
                insertDB();
                jf.dispose();   
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(400,700,70,50);
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                new MainMenu();
            }
        });

        jf.add(jlNama);
        jf.add(username);
        jf.add(jlPassword);
        jf.add(password);
        jf.add(jlEmail);
        jf.add(email);
        jf.add(jlGender);
        jf.add(pria);
        jf.add(wanita);
        jf.add(jlCategory);
        jf.add(kategori);
        jf.add(jlFollowers);
        jf.add(followers);
        jf.add(submit);
        jf.add(back);

        jf.setLayout(null);
        jf.setVisible(true);
    }

    public void insertData(CategoryUser userkategori) {
        user.setUserName(username.getText());
        user.setPassword(password.getText());
        user.setUserEmail(email.getText());
        if (pria.isSelected()) {
            user.setUserGender("Pria");
        } else {
            user.setUserGender("Wanita");
        }
        user.setUserCategory(userkategori);
        user.setUserFollowers(Integer.valueOf(followers.getText()));
    }
}
