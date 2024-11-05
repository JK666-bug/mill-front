import javax.swing.*;
import java.awt.*;

import mill.Login;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;

public class Application {
    public static void main(String[] args) {
        try {
            FlatMacDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("something wrong");
            System.exit(1);
        }
        JFrame frame = new JFrame("Who Wants To Be A Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(UIScale.scale(new Dimension(750, 500)));
        frame.add(new Login(frame));
        frame.setVisible(true);
    }
}
