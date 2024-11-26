package net.heimeng.mill;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatClientProperties;
import net.heimeng.entity.UserCredentials;
import net.heimeng.model.LoginVO;
import net.heimeng.model.R;
import net.heimeng.request.ApiService;
import net.heimeng.storage.TokenStorage;
import net.heimeng.util.OkHttpUtils;
import net.miginfocom.swing.MigLayout;
import okhttp3.Call;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Login Panel
 *
 * @author JK
 */
public class Login extends JPanel {
    private JFrame frame;
    public Login(JFrame frame) {
        init();
    }

    private void afterLogin() {
        this.setVisible(false);
    }

    void init() {
        setLayout(new MigLayout("fill, wrap 2, insets 20", "[center]", "[center]"));

        userName = new JTextField();
        passWord = new JPasswordField();
        rememberMe = new JCheckBox("Remember me");
        login = new JButton("Login");

        login.addActionListener(e -> {
            String userName2 = userName.getText().trim();
            String passWord2 = String.valueOf(passWord.getPassword());


            if (!userName2.isEmpty() && !passWord2.isEmpty()) {
                UserCredentials loginCredentials = new UserCredentials(
                        userName.getText().trim(),
                        String.valueOf(passWord.getPassword())
                );
                OkHttpUtils.builder()
                        .url(ApiService.HOST + "/mill/login") // Ensure this URL is correct
                        .addParam("username", loginCredentials.getUsername())
                        .addParam("password", loginCredentials.getPassword())
                        .post(false)
                        .async(new OkHttpUtils.ICallBack() {
                            @Override
                            public void onSuccessful(Call call, String data) {

                                // Our server returns a JSON object containing the token
                                try {
                                    new GameFrame();
                                    // Parse the response to get the token
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    R<LoginVO> response = objectMapper.readValue(data, new TypeReference<>() {});
                                    String token = response.getData().getToken();

//                                    afterLogin();

                                    // Save the token
                                    //TokenStorage.setToken(token);

                                    // Optionally, inform the user that Login was successful
                                    JOptionPane.showMessageDialog(null, "Login successful!");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error processing response.");
                                }
                            }

                            @Override
                            public void onFailure(Call call, String errorMsg) {
                                showErrorFrame("Username or Password is incorrect.");
                                JOptionPane.showMessageDialog(null, "Login failed: " + errorMsg);
                            }
                        });
            } else if (userName2.isEmpty()) {
                JFrame errorFrame = new JFrame("Error");
                errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                errorFrame.setSize(300, 150);
                JLabel errorLabel = new JLabel("Please enter your username", SwingConstants.CENTER);
                errorFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
                JButton closeButton = new JButton("close");
                closeButton.addActionListener(e1 -> errorFrame.dispose());

                errorFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);
                errorFrame.setLocationRelativeTo(null);
                errorFrame.setVisible(true);
            } else {
                JFrame errorFrame = new JFrame("Error");
                errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                errorFrame.setSize(300, 150);
                JLabel errorLabel = new JLabel("Please enter your password", SwingConstants.CENTER);
                errorFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
                JButton closeButton = new JButton("close");
                closeButton.addActionListener(e2 -> errorFrame.dispose());
                errorFrame.getContentPane().add(closeButton, BorderLayout.SOUTH);
                errorFrame.setLocationRelativeTo(null);
                errorFrame.setVisible(true);
            }
        });

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new MigLayout("wrap,insets 38 38 38 38", "fill,240:260"));
        panel = new JPanel(new MigLayout("wrap,insets 38 38 38 38", "fill,240:260"));
        passWord.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        login.putClientProperty(FlatClientProperties.STYLE,
                "[dark]background:lighten(@background,10%);"
        );

        userName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
        passWord.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        label = new JLabel("Welcome back!");
        label2 = new JLabel("Please sign in to access your account");
        label.putClientProperty(FlatClientProperties.STYLE, "font:bold +12");
        addAll();
        add(backgroundPanel, BorderLayout.WEST);
    }

    private void showErrorFrame(String message) {
        // Create a new frame for the error message
        JFrame errorFrame = new JFrame("Login Error");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(300, 150);
        errorFrame.setLocationRelativeTo(null); // Center the frame

        // Create a panel to hold the message and button
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> errorFrame.dispose()); // Close the frame

        panel.add(errorLabel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        errorFrame.add(panel);
        errorFrame.setVisible(true); // Show the error frame
    }

    private void addAll() {
        panel.add(label);
        panel.add(label2);
        panel.add(new JLabel("Username"));
        panel.add(userName);
        panel.add(new JLabel("Password"));
        panel.add(passWord);
        panel.add(rememberMe);
        panel.add(login);
        panel.add(createSignUpLabel());
        add(panel);
    }

    private Component createSignUpLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "background:null");
        JButton cmdRegister = new JButton("Sign up");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(e -> {
            new Register();
        });
        JLabel label = new JLabel("Don't have an account?");
        panel.add(label);
        panel.add(cmdRegister);
        return panel;
    }

    private JTextField userName;
    private JPasswordField passWord;
    private JCheckBox rememberMe;
    private JButton login;
    private JLabel label2;
    private JPanel panel;
    private JLabel label;

}
