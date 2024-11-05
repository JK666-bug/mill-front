package Mill;


import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
   public GameFrame(){
       init();
   }
   void init(){
       JFrame gameFrame = new JFrame();
       gameFrame.setLayout(new BorderLayout());
       gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       gameFrame.setSize(1100, 500);
       gameFrame.setLocationRelativeTo(null);
       gameFrame.setMinimumSize(UIScale.scale(new Dimension(750, 500)));

       chatPanel = new Chatpanel();
       leftPanel = new Leftpanel();
       // 使用 JSplitPane 分割 chatPanel 和 leftPanel
       JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, chatPanel);
       splitPane.setDividerLocation(0.3); // 设置分割位置为 50%，即平分窗口
       splitPane.setResizeWeight(0.3);    // 设置 resize 时两侧组件的比例
       splitPane.setOneTouchExpandable(true); // 添加展开/收缩按钮

       // 添加分割面板到主框架中
       gameFrame.add(splitPane, BorderLayout.CENTER);
       gameFrame.setVisible(true);


   }
   public static Chatpanel chatPanel;
   public static Leftpanel leftPanel;


}
