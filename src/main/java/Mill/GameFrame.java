package Mill;

import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        init();
    }

    void init() {
        JFrame gameFrame = new JFrame("Who Wants To Be A Millionaire");
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1100, 500);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setMinimumSize(UIScale.scale(new Dimension(750, 500)));

        chatPanel = new ChatPanel();
        leftPanel = new LeftPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, chatPanel);
        splitPane.setDividerLocation(0.3);
        splitPane.setResizeWeight(0.3);
        splitPane.setOneTouchExpandable(true);
        gameFrame.add(splitPane, BorderLayout.CENTER);
        gameFrame.setVisible(true);


   }

   public static ChatPanel chatPanel;
   public static LeftPanel leftPanel;

}
