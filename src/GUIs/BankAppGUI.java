package GUIs;
import db_objs.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BankAppGUI extends BaseGUI implements ActionListener {
    public BankAppGUI(User user) {
        super("BankApp", user);

    }

    private JTextField currentBalanceField;

    public JTextField getCurrentBalanceField() {
        return currentBalanceField;
    }

    @Override
    protected void addGuiComponents() {
        //welcome message
        JLabel welcomeMessage = new JLabel("<html>" + "<body style='text-align:center'>" + "<b>Hello " + user.getUsername() + "</b><br></body></html>");
        welcomeMessage.setBounds(0, 20, getWidth() - 10, 40);
        welcomeMessage.setFont(new Font("Dialog", Font.PLAIN, 16));
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessage);

        //currentBalance
        JLabel currentBalance = new JLabel("current Balance");
        currentBalance.setBounds(0, 80, getWidth() - 10, 30);
        currentBalance.setFont(new Font("Dialog", Font.BOLD, 22));
        currentBalance.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalance);

        //currentBalanceField
        currentBalanceField = new JTextField(user.getCurrentBalance() + " THB");
        currentBalanceField.setBounds(15, 120, getWidth() - 50, 40);
        currentBalanceField.setFont(new Font("Dialog", Font.BOLD, 22));
        currentBalanceField.setHorizontalAlignment(SwingConstants.RIGHT);
        currentBalanceField.setEditable(false);
        add(currentBalanceField);

        //deposit button
        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(15, 180, getWidth() - 50, 50);
        depositButton.setFont(new Font("Dialog", Font.BOLD, 22));
        depositButton.addActionListener(this);
        add(depositButton);

        //withdraw button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(15, 250, getWidth() - 50, 50);
        withdrawButton.setFont(new Font("Dialog", Font.BOLD, 22));
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        //Transaction button
        JButton transactionButton = new JButton("Past Transaction");
        transactionButton.setBounds(15, 320, getWidth() - 50, 50);
        transactionButton.setFont(new Font("Dialog", Font.BOLD, 22));
        transactionButton.addActionListener(this);
        add(transactionButton);

        //Transfer button
        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(15, 390, getWidth() - 50, 50);
        transferButton.setFont(new Font("Dialog", Font.BOLD, 22));
        transferButton.addActionListener(this);
        add(transferButton);

        //logoutButton
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(15, 500, getWidth() - 50, 50);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 22));
        logoutButton.addActionListener(this);
//        logoutButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                BankAppGUI.this.dispose();
//
//                new LoginGUI().setVisible(true);
//            }
//        });
        add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        if (buttonPressed.equalsIgnoreCase("Logout")) {
            new LoginGUI().setVisible(true);
            this.dispose();
            return;
        }

        BankingAppDialog bankingAppDialog = new BankingAppDialog(this, user);
        bankingAppDialog.setTitle(buttonPressed);
        if (buttonPressed.equalsIgnoreCase("Deposit") || buttonPressed.equalsIgnoreCase("Withdraw")
                || buttonPressed.equalsIgnoreCase("Transfer")) {
            bankingAppDialog.addCurrentBalanceAmount();
            bankingAppDialog.addActionButton(buttonPressed);
            if(buttonPressed.equalsIgnoreCase("Transfer")){
                bankingAppDialog.addUserField();
            }
            bankingAppDialog.setVisible(true);
        }else if(buttonPressed.equalsIgnoreCase("Past Transaction")){
            bankingAppDialog.addPastTransactionComponents();
        }
        bankingAppDialog.setVisible(true);

    }
}

