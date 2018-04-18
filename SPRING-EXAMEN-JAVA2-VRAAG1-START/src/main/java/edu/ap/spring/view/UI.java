package edu.ap.spring.view;

import edu.ap.spring.model.EightBall;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;

@Component
public class UI implements InitializingBean {

    @Autowired
    EightBall eightBall;

    private JFrame jFrame;
    private JLabel jLabel, answer;
    private JTextField question;
    private JPanel jPanel;
    private JButton button;

	public UI() {};

    public void setupUI() {
        jFrame = new JFrame("Eightball");
        jFrame.setLayout(new FlowLayout());

        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2, 2));

        jLabel = new JLabel("Ask a question: ");
        question = new JTextField(15);

        answer = new JLabel("");

        button = new JButton();
        button.setText("Ask!");
        button.setTransferHandler(new TransferHandler("text"));
        button.addActionListener(this::whenButtonClicked);

        jPanel.add(jLabel);
        jPanel.add(question);
        jPanel.add(answer);
        jPanel.add(button);

        jFrame.add(jPanel);

        jFrame.setSize(400, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void whenButtonClicked(ActionEvent actionEvent) {
        String question = this.getQuestion().getText();
        String mysticAnswer = eightBall.getRandomAnswer(question);
        setAnswer(mysticAnswer);
    }

    public JTextField getQuestion() { return this.question; }

    public void setAnswer(String answer) {
        this.answer.setText(answer);
        this.answer.updateUI();

    }

	@Override
	public void afterPropertiesSet() throws Exception {
		System.setProperty("java.awt.headless", "false");
	}
}
