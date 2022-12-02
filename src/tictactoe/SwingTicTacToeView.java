package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

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
  JButton quitButton = new JButton();
  JButton restartButton = new JButton();
  ImageIcon jordan;
  ImageIcon femi;

  public SwingTicTacToeView(String caption, TicTacToeController controller) {
    super(caption);


    this.frame = new JFrame(caption);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(600, 600);
    //this.setLocation(400, 200);
    this.frame.setLayout(new BorderLayout());
    this.frame.setVisible(true);
    this.frame.setResizable(false);

    //Title
    this.createTitle();

    //Buttons
    this.addActionButtons();

    //Create Board
    this.addBoard();

    //Add Faces
    this.addFacesToBoard();


    pack();
    //setVisible(true);

  }

  @Override
  public void addFeatures(TicTacToeFeatures features) {

    //Quit button
    this.quitButton.addActionListener(ext -> features.exitProgram());

    //Restart Button
    this.restartButton.addActionListener(ext -> features.restartGame());

    //Iteration button
    int button = 0;
    for (int i = 0 ; i < 3 ; i++) {
      for (int j = 0; j < 3; j++){
        int finalI = i; int finalJ = j; int finalButton = button;
        this.buttonPanel[button].addActionListener(evt -> features.playAtPosition(finalButton,finalI, finalJ));
        button++;
      }
    }
  }


  @Override
  public void setTitleText(Player s) {
    this.textField.setText("Turn: " + (s.toString().equals("X")? "Femi" : "Jordan"));
  }


  @Override
  public void setTextButton(int button, String text) {
//    buttonPanel[button].setText(text);
    buttonPanel[button].setIcon(text.equals("X")? this.femi : this.jordan);

  }

  /**
   * Format the title once, and we can use this to show data
   */
  private void createTitle(){

    this.textField.setBackground(new Color(0, 0, 0));
    this.textField.setForeground(new Color(255, 255, 255));
    this.textField.setFont(new Font("Arial", Font.BOLD, 50));
    this.textField.setHorizontalAlignment(JLabel.CENTER);
    this.textField.setOpaque(true);

    this.textPanel.setLayout(new BorderLayout());
    this.textPanel.setBounds(0, 0, 400, 100);

    this.textPanel.add(this.textField);

    this.setTitleText(Player.X);
    this.frame.add(textPanel, BorderLayout.NORTH);

  }

  /**
   * Add the action area buttons, this will include the quit button and restart
   */
  private void addActionButtons() {

    this.quitButton.setText("Quit");
    this.restartButton.setText("Restart");

    //Buttons Area
    JPanel panel= new JPanel();
    panel.add(quitButton);
    panel.add(restartButton);

    this.frame.add(panel, BorderLayout.EAST);
  }

  /**
   * Create the board layout
   */
  private void addBoard() {

    this.gridPanel.setLayout(new GridLayout(3, 3));
    for(int i = 0; i < buttonPanel.length; i++){
      this.buttonPanel[i] = new JButton();
      this.gridPanel.add(buttonPanel[i]);
      this.buttonPanel[i].setFont(new Font("Arial", Font.BOLD, 60));
      this.buttonPanel[i].setFocusable(false);
    }

    this.frame.add(this.gridPanel);
  }

  /**
   * Add faces to the board, that way we just need to calculate once
   */
  private void addFacesToBoard() {
    try{

      ImageIcon icon = new ImageIcon("res/femi.png");
      Image image = icon.getImage();
      Image image2 = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
      femi = new ImageIcon(image2);

      icon = new ImageIcon("res/jordan.png");
      image = icon.getImage();
      image2 = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
      jordan = new ImageIcon(image2);

    } catch (Exception ex) {
      System.out.println(ex);
    }
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
  public void cleanBoard(){

    ImageIcon icon = new ImageIcon("");
    Arrays.stream(this.buttonPanel).forEach(x -> x.setIcon(icon));
    setTitleText(Player.X);
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
