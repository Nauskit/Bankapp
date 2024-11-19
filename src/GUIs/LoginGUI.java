package GUIs;

import db_objs.MyJDBC;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends BaseGUI {
    public LoginGUI(){
       super("Banking App Login");
    }

    @Override
    protected void addGuiComponents() {
        //title
        JLabel bankingAppLabel = new JLabel("Banking Application");
        bankingAppLabel.setBounds(0,20,super.getWidth(),40);
        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD,32));
        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(bankingAppLabel);

        //usernameLabel
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(20,120,getWidth()-30,24);
        usernameLabel.setFont(new Font("Dialog",Font.PLAIN,20));
        add(usernameLabel);

        //usernameField
        JTextField usernameField = new JTextField();
        usernameField.setBounds(20,160,getWidth()-50,40);
        usernameField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(usernameField);

        //passwordLabel
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20,280,getWidth()-30,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(passwordLabel);

        //passwordField
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,320,getWidth()-50,40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(passwordField);

        //loginButton
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20,450,getWidth()-50,40);
        loginButton.setFont(new Font("Dialog",Font.BOLD,28));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                User user = MyJDBC.validateLogin(username, password);


                if (user != null) {
                    LoginGUI.this.dispose();
                    BankAppGUI bankAppGUI = new BankAppGUI(user);
                    bankAppGUI.setVisible(true);
                    JOptionPane.showMessageDialog(bankAppGUI,"Login Successfully");
                }else{
                    JOptionPane.showMessageDialog(LoginGUI.this,"Login Faild!!");
                }
            }
        });
        add(loginButton);


        //registerText
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Register here if you don't have an account</a></html>");
        registerLabel.setBounds(0,510,getWidth()-10,30);
        registerLabel.setFont(new Font("Dialog", Font.BOLD,18));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGUI.this.dispose();

                new RegisterGUI().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
