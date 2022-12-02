package tictactoe;

public interface TicTacToeView {

  /**
   * Set the label that is showing what the model stores.
   * @param s the string to echo.
   */
  void setEchoOutput(String s);

  void resetFocus();

  void addFeatures(TicTacToeFeatures features);

}

