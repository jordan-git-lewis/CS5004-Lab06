package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JFrameView extends JFrame implements IView {
  private final JLabel display;
  private final JButton echoButton;
  private final JButton exitButton;
  private final JButton toggleButton;
  private final JButton upperCaseButton;
  private final JButton lowerCaseButton;
  private final JButton invertStringButton;
  private final JButton noVowelsButton;
  private final JTextField input;

  public JFrameView(String caption) {
    super(caption);

    setSize(600, 300);
    setLocation(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     this.setResizable(false);
     this.setMinimumSize(new Dimension(300,300));

    this.setLayout(new FlowLayout());

    display = new JLabel("Write anything here");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));


    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);

    //toggle color button
    toggleButton = new JButton("Toggle color");
    toggleButton.setActionCommand("Toggle color");
    this.add(toggleButton);

    // upper case button
    upperCaseButton = new JButton("Make uppercase");
    upperCaseButton.setActionCommand("Uppercase");
    this.add(upperCaseButton);

    // lower case button
    lowerCaseButton = new JButton("Restore text");
    lowerCaseButton.setActionCommand("Lowercase");
    this.add(lowerCaseButton);

    // Invert String button
    invertStringButton = new JButton("Invert text");
    invertStringButton.setActionCommand("Invert");
    this.add(invertStringButton);

    // No Vowels button
    noVowelsButton = new JButton("Remove vowels");
    noVowelsButton.setActionCommand("No Vowels");
    this.add(noVowelsButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);


    pack();
    setVisible(true);

  }

  @Override
  public void addFeatures(Features features) {
    echoButton.addActionListener(evt -> features.echoOutput(input.getText()));
    toggleButton.addActionListener(evt -> features.toggleColor());
    upperCaseButton.addActionListener(evt->features.makeUppercase());
    lowerCaseButton.addActionListener(evt->features.restoreLowercase());
    invertStringButton.addActionListener(evt -> features.invertString());
    noVowelsButton.addActionListener(evt -> features.removeVowels());
    exitButton.addActionListener(evt -> features.exitProgram());
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 't') {
          features.toggleColor();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
          features.makeUppercase();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
          features.restoreLowercase();
        }
      }
    });
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
  public void toggleColor() {
    if (this.display.getForeground().equals(Color.RED))
      this.display.setForeground(Color.BLACK);
    else
      this.display.setForeground(Color.RED);
  }


  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }


}
