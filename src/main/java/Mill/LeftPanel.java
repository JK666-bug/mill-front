package Mill;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import Util.MethodUtil;

public class LeftPanel extends JPanel {

    public static JLabel number;
    public static JPanel ScorePanel;
    public static JButton buttonA;
    public static JButton buttonB;
    public static JButton buttonC;
    public static JLabel label1;
    public static JLabel label2;
    public static JLabel label3;
    public static JLabel label4;
    public static JLabel label5;
    public static JLabel labelScore;

    public LeftPanel() {
        init();
    }

    void init() {
        setLayout(new MigLayout("wrap,fill,insets 15 8 8 8", "[fill,270::]", "[grow 0]0[fill]"));

        panel = new JPanel(new MigLayout("wrap,fillx,gapy 3,insets 8 8 8 8", "[fill]"));

        addButtons();
        changeScore("0");
        addScoreList();
    }

    private void addButtons() {
        JPanel buttonpanel = new JPanel(new MigLayout("gap 15", "[fill]", "[center]"));
        buttonA = new JButton("VOTE");
        buttonA.addActionListener(e -> MethodUtil.runWithThread(() -> {
            ChatPanel.showArea.append("If you trust me, you can choose " + getVotedAnswer() + "\n");
            buttonA.setEnabled(false);
        }
        ));
        buttonA.setFont(new Font("HarmonyOS Sans SC Black", Font.PLAIN, 15));
        buttonB = new JButton("CALL");
        buttonB.setFont(new Font("HarmonyOS Sans SC Black", Font.PLAIN, 15));
        buttonB.addActionListener(e -> MethodUtil.runWithThread(() -> {
                    ChatPanel.showArea.append("If you trust me, you can choose " + getVotedAnswer() + "\n");
                    buttonB.setEnabled(false);
                }

        ));
        buttonC = new JButton("HINT");
        buttonC.addActionListener(e -> MethodUtil.runWithThread(() -> {
                    ChatPanel.showArea.append(getHintAnswer() + " is one of the wrong answers" + "\n");
                    buttonC.setEnabled(false);
                }
        ));
        buttonC.setFont(new Font("HarmonyOS Sans SC Black", Font.PLAIN, 15));
        buttonpanel.add(buttonA);
        buttonpanel.add(buttonB);
        buttonpanel.add(buttonC);
        add(buttonpanel);
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
    }

    private String getHintAnswer() {
        Random random = new Random();
        char[] charset = {'A', 'B', 'C', 'D'};
        char selectedChar;
        String target = ChatPanel.answer;
        do {
            int randomIndex = random.nextInt(charset.length);
            selectedChar = charset[randomIndex];
        } while (String.valueOf(selectedChar).equals(target));

        return String.valueOf(selectedChar);
    }

    private String getVotedAnswer() {
        Random random = new Random();
        int randomIndex = random.nextInt(4);
        String randomChar = null;
        switch (randomIndex) {
            case 0:
                randomChar = "A";
                break;
            case 1:
                randomChar = "B";
                break;
            case 2:
                randomChar = "C";
                break;
            case 3:
                randomChar = "D";
                break;
        }
        return randomChar;
    }

    public void changeScore(String x) {
        ScorePanel = new JPanel(new MigLayout("gap 25", "[fill]", "[center]"));
        labelScore = new JLabel("Score:");
        labelScore.setPreferredSize(new Dimension(100, 100));
        labelScore.setFont(new Font("Forte", Font.PLAIN, 40));
        number = new JLabel(x);
        number.setPreferredSize(new Dimension(100, 100));
        number.setFont(new Font("Forte", Font.PLAIN, 80));
        number.setForeground(Color.RED);
        ScorePanel.add(labelScore);
        ScorePanel.add(number);
        add(ScorePanel,"gapy 25");
    }

    private void addScoreList() {
        JPanel scoreList = new JPanel(new MigLayout("wrap,fill,insets 8 8 8 8"));
        scoreList.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        label1 = new JLabel("    Level one                    $100");
        label1.setFont(new Font("华文琥珀", Font.PLAIN, 20));

        label2 = new JLabel("    Level two                    $200");
        label2.setFont(new Font("华文琥珀", Font.PLAIN, 20));

        label3 = new JLabel("    Level three                  $500");
        label3.setFont(new Font("华文琥珀", Font.PLAIN, 20));

        label4 = new JLabel("    Level four                  $1000");
        label4.setFont(new Font("华文琥珀", Font.PLAIN, 20));

        label5 = new JLabel("    Level five                  $2000");
        label5.setFont(new Font("华文琥珀", Font.PLAIN, 20));

        scoreList.add(label5);
        scoreList.add(label4);
        scoreList.add(label3);
        scoreList.add(label2);
        scoreList.add(label1);

        add(scoreList);
    }

    public static JPanel panel;
}
