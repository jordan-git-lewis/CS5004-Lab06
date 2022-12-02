package tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SwingTicTacToeView extends JFrame implements TicTacToeView, ActionListener {

  //private final JLabel display;

  //private final JButton echoButton;
  //private final JButton exitButton;
  TicTacToeController controller;
  JFrame frame;
  JPanel textPanel = new JPanel();
  JPanel gridPanel = new JPanel();
  JButton[] buttonPanel = new JButton[9];
  JLabel textField = new JLabel(); // Labels buttons after they are clicked

  public SwingTicTacToeView(String caption, TicTacToeController controller) {
    super(caption);


    this.frame = new JFrame(caption);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(600, 600);
    //this.setLocation(400, 200);
    this.frame.setLayout(new BorderLayout());
    this.frame.setVisible(true);
    this.frame.setResizable(false);

    this.createTitle();
    this.setTitleText(controller.displayTurn());

    this.gridPanel.setLayout(new GridLayout(3, 3));
    for(int i = 0; i < buttonPanel.length; i++){
      this.buttonPanel[i] = new JButton();
      this.gridPanel.add(buttonPanel[i]);
      this.buttonPanel[i].setFont(new Font("Arial", Font.BOLD, 60));
      this.buttonPanel[i].setFocusable(false);
    }

    this.frame.add(textPanel, BorderLayout.NORTH);
    this.frame.add(this.gridPanel);

    pack();
    //setVisible(true);

  }

  @Override
  public void addFeatures(TicTacToeFeatures features) {

    int button = 0;

    for (int i = 0 ; i < 3 ; i++) {
      for (int j = 0; j < 3; j++){
        int finalI = i;
        int finalJ = j;
        int finalButton = button;
        this.buttonPanel[button].addActionListener(evt -> features.playAtPosition(finalButton,finalI, finalJ));
        button++;
      }
    }
  }


  @Override
  public void setTitleText(Player s) {

    String formattedText = s.toString();
    this.textField.setText("Turn: " + formattedText);
  }


  @Override
  public void setTextButton(int button, String text) {
    buttonPanel[button].setText(text);
  }

  /**
   * Format the title once, and we can use this to show data
   */
  private void createTitle(){
    this.textField.setBackground(new Color(0, 0, 0));
    this.textField.setForeground(new Color(255, 255, 255));
    this.textField.setFont(new Font("Arial", Font.BOLD, 75));
    this.textField.setHorizontalAlignment(JLabel.CENTER);
    this.textField.setOpaque(true);

    this.textPanel.setLayout(new BorderLayout());
    this.textPanel.setBounds(0, 0, 600, 100);

    this.textPanel.add(this.textField);

  }


  /*
    In order to make this frame respond to keyboard events, it must be within strong focus.
    Since there could be multiple components on the screen that listen to keyboard events,
    we must set one as the "currently focussed" one so that all keyboard events are
    passed to that component. This component is said to have "strong focus".

    We do this by first making the component focusable and then requesting focus to it.
    Requesting focus makes the component have focus AND removes focus from whoever had it
    before.
  */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setEchoOutput(String s) {
    //display.setText(s);
  }


  @Override
  public void actionPerformed(ActionEvent e) {


    for (int i = 0; i < 9; i++){
      if(e.getSource() == this.buttonPanel[i]){
        int row = i / 3 + 1;
        int col = i % 3 + 1;
        if(buttonPanel[i].getText() == ""){
          buttonPanel[i].setForeground(new Color(0, 0, 0));

          //This is where bugs occur
//          String str = this.controller.playAtPosition(row, col);
//          buttonPanel[i].setText(str);


        }
      }
    }
  }
}
