package UAS.View;

import javax.swing.*;
import UAS.Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    public MainMenu(){
        //JFrame
        JFrame frame = new JFrame("Menu Utama");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        
        JButton jbLogin = new JButton("Login");
        jbLogin.setBounds(150,40,100,50);
        jbLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginController();
            }
        });

        JButton jbRegister = new JButton("Register");
        jbRegister.setBounds(150,110,100,50);
        jbRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        JButton jbShowUser = new JButton("Show User");
        jbShowUser.setBounds(150,180,100,50);
        jbShowUser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(jbLogin);
        frame.add(jbRegister);
        frame.add(jbShowUser);
        frame.setLayout(null);
        frame.setVisible(true);
    }   
}
