package mill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formdev.flatlaf.FlatClientProperties;
import model.MillDTO;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import util.MethodUtil;
import util.PublicUtils;

public class ChatPanel extends JPanel {
    private Integer score = 0;
    public static JButton buttonA;
    public static JButton buttonB;
    public static JButton buttonC;
    public static JButton buttonD;
    static boolean init = true;
    public static String answer = "A";
    public ChatPanel() {
        init();
    }

    void init() {
        setOpaque(true);
        setLayout(new MigLayout("wrap,fill", "[fill,400::]", "[fill][shrink 0,grow 0]"));
        putClientProperty(FlatClientProperties.STYLE, "background:$Chat.background");
        panelBottom = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        // create button panel
        JButton btnEndGame = new JButton("START");
        btnEndGame.addActionListener(e -> {
            if ("START".equals(btnEndGame.getText())) {
                initAll();
                PublicUtils.refreshContext();
                btnEndGame.setText("END");

                LeftPanel.buttonA.setEnabled(true);
                LeftPanel.buttonB.setEnabled(true);
                LeftPanel.buttonC.setEnabled(true);
                sendChoice("I want to play Who Wants To Be A Millionaire! You should ask me 5 different questions partly. Now let's start!\n");

            } else {
                ChatPanel.showArea.append("Congratulations! You got " + calculateDollars(score) + " dollars!\n");
                btnEndGame.setText("START");
                buttonDisable();
                LeftPanel.buttonA.setEnabled(false);
                LeftPanel.buttonB.setEnabled(false);
                LeftPanel.buttonC.setEnabled(false);
            }
        });

        btnEndGame.setFont(new Font("Serif", Font.PLAIN, 15));
        buttonA = new JButton("A");
        buttonA.setFont(new Font("Serif", Font.PLAIN, 15));
        buttonA.addActionListener(e -> {
            buttonDisable();
            sendChoice("I choose A");
        });

        buttonB = new JButton("B");
        buttonB.setFont(new Font("Serif", Font.PLAIN, 15));
        buttonB.addActionListener(e -> {
            buttonDisable();
            sendChoice("I choose B");
        });

        buttonC = new JButton("C");
        buttonC.setFont(new Font("Serif", Font.PLAIN, 15));
        buttonC.addActionListener(e -> {
            buttonDisable();
            sendChoice("I choose C");
        });

        buttonD = new JButton("D");
        buttonD.setFont(new Font("Serif", Font.PLAIN, 15));
        buttonD.addActionListener(e -> {
            buttonDisable();
            sendChoice("I choose D");
        });

        btnEndGame.setPreferredSize(new Dimension(90, 30));
        buttonA.setPreferredSize(new Dimension(90, 30));
        buttonB.setPreferredSize(new Dimension(90, 30));
        buttonC.setPreferredSize(new Dimension(90, 30));
        buttonD.setPreferredSize(new Dimension(90, 30));
        // put buttons into buttonPanel
        buttonPanel.add(btnEndGame);
        buttonPanel.add(buttonA);
        buttonPanel.add(buttonB);
        buttonPanel.add(buttonC);
        buttonPanel.add(buttonD);

        // add buttonPanel to the bottom of panelBottom
        panelBottom.add(buttonPanel, BorderLayout.NORTH);
        panelBottom.putClientProperty(FlatClientProperties.STYLE, "background:$Chat.background");
        add(panelBottom, "cell 0 1, grow");
        panelBottom.revalidate();
        panelBottom.repaint();
        buttonDisable();

        showArea = new JTextArea(12, 34);
        scrollPane = new JScrollPane(showArea);
        showArea.setEditable(false);
        showArea.setFont(new Font("Arial", Font.PLAIN, 16));
        showArea.setCaretPosition(showArea.getDocument().getLength());
        add(scrollPane, "cell 0 0, grow");
    }

    private void initAll() {
        init = true;
        LeftPanel.buttonA.setVisible(true);
        LeftPanel.buttonB.setVisible(true);
        LeftPanel.buttonC.setVisible(true);
        LeftPanel.number.setText("0");

        LeftPanel.label1.setForeground(Color.white);
        LeftPanel.label2.setForeground(Color.white);
        LeftPanel.label3.setForeground(Color.white);
        LeftPanel.label4.setForeground(Color.white);
        LeftPanel.label5.setForeground(Color.white);
        showArea.setText("Please click button to start\n");
    }

    public void buttonDisable() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void buttonEnable() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    public Integer calculateDollars(Integer score) {
        int money = 0;
        switch (score) {
            case 20:
                money = 100;
                break;
            case 40:
                money = 200;
                break;
            case 60:
                money = 500;
                break;
            case 80:
                money = 1000;
                break;
            case 100:
                money = 2000;
                break;
        }
        return money;
    }

    public void sendChoice(String text) {
        System.out.println("\nThe score has been updated:" + score);
        MethodUtil.runWithThread(() -> {
            MillDTO dto;
            try {
                dto = PublicUtils.sendAndGetMillDTO(text);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            sendChoiceHandler(dto);
            buttonEnable();
            LeftPanel.number.setText(dto.getProgress().toString());
            if (dto.getQuestion() != null) {
                System.out.println(dto.getQuestion().getAnswer());
            }
            if (dto.getQuestion() != null) {
                answer = dto.getQuestion().getAnswer();
            }

            if (init) {
                init = false;
            } else {
                switch (dto.getProgress()) {
                    case 0:
                        buttonDisable();
                        showArea.append("What a shame! You didn't get money, please click END button to restart");
                        break;
                    case 20:
                        LeftPanel.label1.setForeground(new Color(1, 1, 1, 0.5f));
                        LeftPanel.label2.setForeground(Color.YELLOW);
                        break;
                    case 40:
                        LeftPanel.label2.setForeground(new Color(1, 1, 1, 0.5f));
                        LeftPanel.label3.setForeground(Color.YELLOW);
                        break;
                    case 60:
                        LeftPanel.label2.setForeground(new Color(1, 1, 1, 0.5f));
                        LeftPanel.label3.setForeground(Color.YELLOW);
                        break;
                    case 80:
                        LeftPanel.label3.setForeground(new Color(1, 1, 1, 0.5f));
                        LeftPanel.label4.setForeground(Color.YELLOW);
                        break;
                    case 100:
                        LeftPanel.label4.setForeground(new Color(1, 1, 1, 0.5f));
                        LeftPanel.label5.setForeground(Color.YELLOW);
                        break;
                }
            }
        });

    }

    public void sendChoiceHandler(MillDTO dto) {
        // 加减分逻辑
        score = dto.getProgress();
        Integer progress = dto.getProgress();
        System.out.println("The progress is: " + progress);
    }

    private JPanel panelBottom;
    public static JTextArea showArea;
    public static JScrollPane scrollPane;

}

