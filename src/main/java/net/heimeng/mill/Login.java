package net.heimeng.mill;

import com.fasterxml.jackson.core.type.TypeReference;
import com.formdev.flatlaf.FlatClientProperties;
import net.heimeng.model.UserCredentials;
import net.heimeng.model.LoginVO;
import net.heimeng.model.R;
import net.heimeng.request.ApiService;
import net.heimeng.storage.TokenStorage;
import net.heimeng.util.JsonUtils;
import net.heimeng.util.OkHttpUtils;
import net.miginfocom.swing.MigLayout;
import okhttp3.Call;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Login Panel
 *
 * @author JK
 */
public class Login extends JPanel {
    private JFrame frame;
    private JTextField userName;
    private JPasswordField passWord;
    private JCheckBox rememberMe;
    private JButton login;
    private JLabel label2;
    private JPanel panel;
    private JLabel label;

    public Login(JFrame frame) {
        this.frame = frame;
        init();
    }

    private void afterLogin() {
        frame.dispose();
    }

    private void init() {
        setLayout(new MigLayout("fill, wrap 2, insets 20", "[center]", "[center]"));

        userName = new JTextField();
        passWord = new JPasswordField();
        rememberMe = new JCheckBox("Remember me");
        login = new JButton("Login");

        login.addActionListener(e -> handleLogin());

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new MigLayout("wrap, insets 38 38 38 38", "fill, 240:260"));
        panel = new JPanel(new MigLayout("wrap, insets 38 38 38 38", "fill, 240:260"));
        passWord.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        login.putClientProperty(FlatClientProperties.STYLE, "[dark]background:lighten(@background,10%);");

        userName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
        passWord.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        label = new JLabel("Welcome back!");
        label2 = new JLabel("Please sign in to access your account");
        label.putClientProperty(FlatClientProperties.STYLE, "font:bold +12");

        addAll();
        add(backgroundPanel, BorderLayout.WEST);
    }

    private void handleLogin() {
        String userName2 = userName.getText().trim();
        String passWord2 = String.valueOf(passWord.getPassword());

        if (userName2.isEmpty()) {
            showErrorFrame("Please enter your username");
            return;
        }

        if (passWord2.isEmpty()) {
            showErrorFrame("Please enter your password");
            return;
        }

        UserCredentials loginCredentials = new UserCredentials(userName2, passWord2);
        OkHttpUtils.builder()
                .url(ApiService.HOST + "/mill/login")
                .addParam("username", loginCredentials.getUsername())
                .addParam("password", loginCredentials.getPassword())
                .post(false)
                .async(new OkHttpUtils.ICallBack() {
                    @Override
                    public void onSuccessful(Call call, String data) {
                        try {
                            R<LoginVO> response = JsonUtils.parseObject(data, new TypeReference<>() {
                            });
                            if (R.isSuccess(response)) {
                                String token = Objects.requireNonNull(response.getData()).getToken();
                                if (token != null) {
                                    TokenStorage.setToken(token);
                                    Frame frame = new GameFrame();
                                    afterLogin(); // 隐藏登录面板
                                } else {
                                    JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, response.getMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "An error occurred when processing response.");
                        }
                    }

                    @Override
                    public void onFailure(Call call, String errorMsg) {
                        JOptionPane.showMessageDialog(null, "Login failed: " + errorMsg);
                    }
                });
    }

    private void showErrorFrame(String message) {
        JFrame errorFrame = new JFrame("Login Error");
        errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        errorFrame.setSize(300, 150);
        errorFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> errorFrame.dispose());

        panel.add(errorLabel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        errorFrame.add(panel);
        errorFrame.setVisible(true);
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
        cmdRegister.addActionListener(e -> new Register());

        JLabel label = new JLabel("Don't have an account?");
        panel.add(label);
        panel.add(cmdRegister);
        return panel;
    }
}
