package Mill;
import Entity.UserCredentials;
import Entity.UserCredentials;
import Storage.TokenStorage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import request.MillApiService;
import util.JsonUtils;
import util.OkHttpUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class login extends JPanel {
    public login(){
        init();
    }
    void init() {
        setLayout(new MigLayout("fill, wrap 2, insets 20", "[center]", "[center]"));

        userName = new JTextField();
        passWord = new JPasswordField();
        rememberMe = new JCheckBox("Remember me");
        login = new JButton("Login");

        login.addActionListener(e -> {

            UserCredentials loginCredentials = new UserCredentials(
                    userName.getText().trim(),
                    String.valueOf(passWord.getPassword())
            );
            OkHttpUtils.builder()
                    .url(MillApiService.HOST + "/mill") // Ensure this URL is correct
                    .post(JsonUtils.toJsonString(loginCredentials))
                    .async(new OkHttpUtils.ICallBack() {
                        @Override
                        public void onSuccessful(Call call, String data) {
                            // Assume your server returns a JSON object containing the token
                            try {
                                // Parse the response to get the token
                                ObjectMapper objectMapper = new ObjectMapper();
                                Map<String, Object> responseMap = objectMapper.readValue(data, Map.class);
                                String token = (String) responseMap.get("token"); // Adjust the key as per your API response
                                new GameFrame();
                                // Save the token
                                TokenStorage.setToken(token);

                                // Optionally, inform the user that login was successful
                                JOptionPane.showMessageDialog(null, "Login successful!");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error processing response.");
                            }
                        }
                        @Override
                        public void onFailure(Call call, String errorMsg) {
                            showErrorFrame("Username or Password is incorrect.");
                            JOptionPane.showMessageDialog(null, "Login failed: " + errorMsg);
                        }
                    });

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
    private void addAll(){
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
        JButton cmdRegister = new JButton("sign up");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(e -> {
        new Register();
        });
        JLabel label = new JLabel("Don't have an account ?");
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