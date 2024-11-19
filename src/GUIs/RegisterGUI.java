package GUIs;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGUI extends BaseGUI {
    public RegisterGUI(){
        super("Banking App Register");
    }

    @Override
    protected void addGuiComponents() {
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
        passwordLabel.setBounds(20,220,getWidth()-30,24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(passwordLabel);

        //passwordField
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20,260,getWidth()-50,40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(passwordField);

        //rePasswordLabel
        JLabel rePasswordLabel = new JLabel("Re-Password");
        rePasswordLabel.setBounds(20,320,getWidth()-30,24);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN,20));
        add(rePasswordLabel);

        //rePasswordField
        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20,360,getWidth()-50,40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN,28));
        add(rePasswordField);
        //registerButton
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20,460,getWidth()-50,40);
        registerButton.setFont(new Font("Dialog", Font.BOLD,28));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //username
                String username = usernameField.getText();
                //password
                String password = String.valueOf(passwordField.getPassword());
                //re-password
                String rePassword = String.valueOf(rePasswordField.getPassword());

                if(validateUserInput(username,password,rePassword)){
                    if(MyJDBC.register(username,password)){
                        RegisterGUI.this.dispose();
                        LoginGUI loginGUI = new LoginGUI();
                        loginGUI.setVisible(true);
                        JOptionPane.showMessageDialog(loginGUI, "Registered Account Successfully!");
                    }else{
                        JOptionPane.showMessageDialog(RegisterGUI.this, "Error!");
                    }
                }else{
                    JOptionPane.showMessageDialog(RegisterGUI.this,
                            "Error : Username must be at least 6 characters\n" +
                            "and/or Password must match!!");
                }
            }
        });
        add(registerButton);

        //sign-inLabel
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Sign-in here</a></html>");
        loginLabel.setBounds(0,510,getWidth()-10,30);
        loginLabel.setFont(new Font("Dialog", Font.BOLD,18));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterGUI.this.dispose();
                new LoginGUI().setVisible(true);
            }
        });
        add(loginLabel);
    }
    private boolean validateUserInput(String username, String password, String rePassword){
        if(username.length() == 0 || password.length() == 0 || rePassword.length() == 0)
            return false;
        if(username.length() < 6)
            return false;
        if(!password.equals(rePassword))
            return false;

        return true;
    }
}
