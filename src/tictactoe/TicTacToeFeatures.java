package tictactoe;

public interface TicTacToeFeatures {

  /**
   * Allows user to exit the game of tictactoe
   */
  void exitProgram();

  /**
   * Connects controller to TicTacToe model move() method. Allows user to play at a given position
   *
   * @param button assigns the Player enum to a button once the user has played
   * @param row the row being played at
   * @param col the column being played at
   * @return 'X' or 'O' depending on which player has moved
   */
  String playAtPosition(int button, int row, int col);

  /**
   * Allows users to reset the game without having to rerun the application
   */
  void restartGame();

  /**
   * Retrieves which player turn it is
   * @return the Player enum X or O
   */
  Player displayTurn();

  void playGame();
}
