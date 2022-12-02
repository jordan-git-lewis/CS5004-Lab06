package tictactoe;

public interface TicTacToeView {

  /**
   * Set the label that is showing what the model stores.
   * @param s the string to echo.
   */
  void setEchoOutput(String s);

  /**
   * Resets the focus following a button press
   */
  void resetFocus();

  void addFeatures(TicTacToeFeatures features);

  /**
   * Set the text for a button
   * @param button position of this button
   * @param displayTurn Text to display
   */
  void setTextButton(int button, String displayTurn);

  /**
   * Change the title text
   * @param s Text to display at the top
   */
  void setTitleText(Player s);
}

