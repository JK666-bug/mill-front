package Mill;

import Entity.UserCredentials;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;
import Request.LocalApiService;
import util.JsonUtils;
import util.OkHttpUtils;

import javax.swing.*;
import java.awt.*;

import static Mill.Leftpanel.panel;


public class Register extends JFrame {
    public Register() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setMinimumSize(UIScale.scale(new Dimension(750, 500)));
        setLayout(new MigLayout("fill,insets 22", "[center]", "[center]"));

        userName = new JTextField();
        passWord = new JPasswordField();
        confirmPassword = new JPasswordField();
        cmdRegister = new JButton("Sign Up");
        panel = new JPanel(new MigLayout("wrap,insets 40 40 40 40", "[fill,350]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[dark]background:lighten(@background,3%)");

        userName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username");
        passWord.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        confirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Conform your password");
        passWord.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        confirmPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");

        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "[dark]background:lighten(@background,10%);"
        );

        lbTitle = new JLabel("Welcome to our Agent Center");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:bold +8");

        panelGender = (JComponent) createGenderPanel();
        cmdRegister.addActionListener(e -> register());
        addAll();
    }
     private void addAll() {
            panel.add(lbTitle);
            panel.add(new JLabel("Gender"));
            panel.add(panelGender);
            panel.add(new JSeparator());
            panel.add(new JLabel("Username"));
            panel.add(userName);
            panel.add(new JLabel("Password"));
            panel.add(passWord);
            panel.add(new JLabel("Confirm Password"));
            panel.add(confirmPassword);
            panel.add(cmdRegister);
            panel.add(createLoginLabel());
            add(panel);
            setVisible(true);
        }

    private Component createGenderPanel() {
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        groupGender = new ButtonGroup();
        groupGender.add(male);
        groupGender.add(female);
        male.setSelected(true);
        panel.add(male);
        panel.add(female);
        return panel;
    }

    private Component createLoginLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdLogin = new JButton("Sign in");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdLogin.setContentAreaFilled(false);
        cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogin.addActionListener(e -> {
            dispose();
        });
        JLabel label = new JLabel("Already have an account ?");
        label.putClientProperty(FlatClientProperties.STYLE, "[dark]foreground:darken(@foreground,25%)");
        panel.add(label);
        panel.add(cmdLogin);
        return panel;
    }

    private void register() {
        String userName2 = userName.getText().trim();
        String password2 = String.valueOf(passWord.getPassword());
        System.out.println(userName2);
        if(checkPassword()&& !userName2.isEmpty()){

        UserCredentials registerCredentials = new UserCredentials(userName2, password2);

        String s = OkHttpUtils.builder()
                .url(LocalApiService.HOST + "/mill/register")
                .post(JsonUtils.toJsonString(registerCredentials)) // 将 credentials 转换为 JSON 字符串
                .async();
        System.out.println(JsonUtils.toJsonString(s));

        JFrame successFrame = new JFrame("Successful");
        successFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        successFrame.setSize(300, 150);
        JLabel errorLabel = new JLabel("Register account successfully", SwingConstants.CENTER);
        successFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(e -> successFrame.dispose());
        successFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);
        successFrame.setLocationRelativeTo(null);
        successFrame.setVisible(true);
    }
    else if(checkPassword()&&userName2.isEmpty()) {
        JFrame errorFrame = new JFrame("Error");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(300, 150);
        JLabel errorLabel = new JLabel("Please enter the username", SwingConstants.CENTER);
        errorFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(e -> errorFrame.dispose());
        errorFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);
        errorFrame.setLocationRelativeTo(null);
        errorFrame.setVisible(true);}

    else{JFrame errorFrame = new JFrame("Error");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(300, 150);
        JLabel errorLabel = new JLabel("Two password input isn't same", SwingConstants.CENTER);
        errorFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(e -> errorFrame.dispose());
        errorFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);
        errorFrame.setLocationRelativeTo(null);
        errorFrame.setVisible(true);
    }
}


    private boolean checkPassword() {
        String password = String.valueOf(passWord.getPassword());
        String confirmPassword = String.valueOf(this.confirmPassword.getPassword());
        return password.equals(confirmPassword);
    }

    private JRadioButton male;
    private JRadioButton female;
    private JTextField userName;
    private JPasswordField passWord;
    private JPasswordField confirmPassword;
    private ButtonGroup groupGender;
    private JButton cmdRegister;
    private JLabel lbTitle;
    private JComponent panelGender;

}
