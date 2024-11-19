package GUIs;

import db_objs.User;

import javax.swing.*;

public abstract class BaseGUI extends JFrame {
    protected User user;
    public BaseGUI(String title){
        initialize(title);
    }
    public BaseGUI(String title, User user){
        this.user = user;
        initialize(title);
    }
    private void initialize(String title){
        setTitle(title);
        setSize(420,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    protected abstract void addGuiComponents();

}
