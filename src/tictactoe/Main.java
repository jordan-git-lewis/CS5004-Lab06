package tictactoe;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Run a Tic Tac Toe game interactively on the console.
 */
public class Main {
  /**
   * Run a Tic Tac Toe game interactively on the console.
   */
  public static void main(String[] args) {
    TicTacToe m = new TicTacToeModel();
    TicTacToeController c = new TicTacToeController(m);
    TicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe", c);
    c.setView(v);
  }
}
