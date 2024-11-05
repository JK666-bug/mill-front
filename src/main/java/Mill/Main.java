package Mill;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;

public class Main {
    public static void main(String[] args) {
        try {
            FlatMacDarkLaf.setup();
            //UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("something wrong");
            System.exit(1);
        }
        JFrame frame = new JFrame("Who wants to be a millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(UIScale.scale(new Dimension(750, 500)));
        frame.add(new login());
        frame.setVisible(true);
    }
}