package tictactoe;

public interface TicTacToeFeatures {

  void echoOutput(String typed);

  void exitProgram();

  String playAtPosition(int row, int col);

  void restartGame();

  Player displayTurn();

  void playGame();
}
