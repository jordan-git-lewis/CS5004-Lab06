package tictactoe;

public interface TicTacToeView {

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

  /**
   * Clean the board and reset the game
   */
  void cleanBoard();
}

