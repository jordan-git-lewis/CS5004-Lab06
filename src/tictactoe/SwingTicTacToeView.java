package tictactoe;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import javax.swing.*;

/**
 * The class that implements the view for our TicTacToe game. This view utilizes Java Swing and
 * displays our game on a 3x3 grid of clickable buttons
 */
public class SwingTicTacToeView extends JFrame implements TicTacToeView {

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

  /**
   * The constructor for our TicTacToe view. It comprised of a caption, which acts as the title for
   * our application and a TicTacToe controller, which relays the inputs from our view to our
   * TicTacToe model
   *
   * @param caption    The title of our frame
   * @param controller The TicTacToe controller to connect to the view
   */
  public SwingTicTacToeView(String caption, TicTacToeController controller) {
    super(caption);

    this.controller = controller;
    // Create the main frame that our buttons and text will display on
    this.frame = new JFrame(caption);
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setSize(800, 800);
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
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int finalI = i;
        int finalJ = j;
        int finalButton = button;
        this.buttonPanel[button].addActionListener(
            evt -> features.playAtPosition(finalButton, finalI, finalJ));
        button++;
      }
    }
  }


  @Override
  public void setTitleText(Player s) {

    if (!controller.isGameOver()){
      this.textField.setText("Turn: " + (s.toString().equals("X") ? "Femi" : "Jordan"));
    }
    else{
      if (controller.displayWinner() == null){
        this.textField.setText("Tie Game! No Winners");
      }
      else{
        String winner = controller.displayWinner().toString();
        this.textField.setText("Game Over! " + (winner.equals("X") ? "Femi" : "Jordan") + " Wins!");
      }
    }

  }


  @Override
  public void setTextButton(int button, String text) {
//    buttonPanel[button].setText(text);
    //Switch sides if X jordan
    buttonPanel[button].setIcon(text.equals("X") ? this.jordan : this.femi);

  }

  /**
   * Format the title once, and we can use this to show data
   */
  private void createTitle() {

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
    JPanel panel = new JPanel();
    panel.add(quitButton);
    panel.add(restartButton);

    this.frame.add(panel, BorderLayout.EAST);
  }

  /**
   * Create the board layout
   */
  private void addBoard() {

    this.gridPanel.setLayout(new GridLayout(3, 3));
    for (int i = 0; i < buttonPanel.length; i++) {
      this.buttonPanel[i] = new JButton();
      this.gridPanel.add(buttonPanel[i]);
      this.buttonPanel[i].setFocusable(false);
    }

    this.frame.add(this.gridPanel);
  }

  /**
   * Add faces to the board, that way we just need to calculate once
   */
  private void addFacesToBoard() {
    try {

      URL femiPath = this.getClass().getClassLoader().getResource("femi.png");
      if(femiPath != null) {
        ImageIcon icon = new ImageIcon(femiPath);
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        femi = new ImageIcon(image2);
      }

      URL jordanPath = this.getClass().getClassLoader().getResource("jordan.png");
      if(jordanPath != null) {
        ImageIcon icon = new ImageIcon(jordanPath);
        Image image = icon.getImage();
        Image image2 = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        jordan = new ImageIcon(image2);
      }
    } catch (Exception ex) {
      //TODO add additional behaviour
    }
  }


  @Override
  public void cleanBoard() {

    ImageIcon icon = new ImageIcon("");
    Arrays.stream(this.buttonPanel).forEach(x -> x.setIcon(icon));
    setTitleText(Player.X);
  }

}
