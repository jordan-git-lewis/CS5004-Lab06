package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class representation of a game of Tic Tac Toe. This class acts as the model for our game
 * implementing the TicTacToe interface
 */
public class TicTacToeModel implements TicTacToe {

  private Player[][] board;
  private int turnCount;
  // List of constants
  private final static int MAXTURNS = 9;
  private final static int ROWSIZE = 3;
  private final static int COLUMNSIZE = 3;
  private final static int WINCONDITION = 3;


  /**
   * Constructs a TicTacToeModel object. A game of tic tac toe consists of a board (empty to start)
   * and a turn count which tracks the number of turns played.
   */
  public TicTacToeModel() {
    this.board = new Player[COLUMNSIZE][ROWSIZE];
    this.turnCount = 1;
  }

  /**
   * Helper function that checks the rows and columns to see if a player has won
   *
   * @return Player.X if player x has won, Player.O if player o has won, or null if neither have won
   */
  private Player checkRowCol() {
    int xColumnCount = 0;
    int oColumnCount = 0;
    int xRowCount = 0;
    int oRowCount = 0;

    for (int i = 0; i < ROWSIZE; i++) {
      for (int j = 0; j < COLUMNSIZE; j++) {
        // Check column for x or o
        if (getMarkAt(i, j) == Player.X) {
          xColumnCount += 1;
        } else if (getMarkAt(i, j) == Player.O) {
          oColumnCount += 1;
        }
        //Check row for x or o
        if (getMarkAt(j, i) == Player.X) {
          xRowCount += 1;
        } else if (getMarkAt(j, i) == Player.O) {
          oRowCount += 1;
        }
      }
      // If either player has reached a count of 3 return Player enum
      if (xColumnCount == WINCONDITION || xRowCount == WINCONDITION) {
        return Player.X;
      } else if (oColumnCount == WINCONDITION || oRowCount == WINCONDITION) {
        return Player.O;
      }
      //Else set counters back to 0 and iterate
      else {
        xColumnCount = 0;
        xRowCount = 0;
        oColumnCount = 0;
        oRowCount = 0;
      }
    }
    return null;
  }

  /**
   * Helper function that checks the two possible diagonal lines to see if a player has won
   *
   * @return Player.X if player x has won, Player.O if player o has won, or null if neither have won
   */
  private Player checkDiagonal() {
    int xCount = 0;
    int oCount = 0;

    // Check middle, if null then don't need to check other values
    if (getMarkAt(1, 1) == null) {
      return null;
    } else {
      // Diagonal upper left to lower right
      for (int i = 0; i < ROWSIZE; i++) {
        if (getMarkAt(i, i) == Player.X) {
          xCount += 1;
        } else if (getMarkAt(i, i) == Player.O) {
          oCount += 1;
        }
      }
      // Diagonal lower left to upper right or other diagonal count = 3
      if (getMarkAt(0, 2) == Player.X && getMarkAt(1, 1) == Player.X
          && getMarkAt(2, 0) == Player.X || xCount == WINCONDITION) {
        return Player.X;
      } else if (getMarkAt(0, 2) == Player.O && getMarkAt(1, 1) == Player.O
          && getMarkAt(2, 0) == Player.O || oCount == WINCONDITION) {
        return Player.O;
      }
    }
    return null;
  }

  @Override
  public Player getWinner() {
    if (checkRowCol() != null) {
      return checkRowCol();
    } else if (checkDiagonal() != null) {
      return checkDiagonal();
    }

    return null;
  }


  @Override
  public boolean isGameOver() {
    if (getWinner() == null && this.turnCount > MAXTURNS) {
      return true;
    } else {
      return getWinner() != null;
    }
  }


  @Override
  public Player getTurn() {
    if (this.turnCount % 2 == 1) {
      return Player.X;
    } else {
      return Player.O;
    }
  }


  @Override
  public Player getMarkAt(int r, int c) {
    if (r >= 0 && r <= 2 && c >= 0 && c <= 2) {
      return this.board[r][c];
    } else {
      throw new IllegalArgumentException("Mark request is out of bounds of game board");
    }
  }


  @Override
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {

    if (isGameOver()) {
      throw new IllegalStateException("Board is full, Game Over. No more moves can be made");
    }
    if (r >= 0 && r <= 2 && c >= 0 && c <= 2) {
      if (this.board[r][c] == Player.O || this.board[r][c] == Player.X) {
        throw new IllegalArgumentException("Player has already moved here");
      } else {
        // play at position and increment count
        this.board[r][c] = getTurn();
        this.turnCount++;
      }
    } else {
      throw new IllegalArgumentException("Invalid coordinates, pick a location between "
          + "(0, 0) and (2, 2)");
    }
  }

  @Override
  public Player[][] getBoard() {
    Player[][] boardCopy = new Player[ROWSIZE][COLUMNSIZE];
    // Copy this.board into new board array and return it
    for (int i = 0; i < this.board.length; i++) {
      System.arraycopy(this.board[i], 0, boardCopy[i], 0, this.board[i].length);
    }
    return boardCopy;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
        row -> " " + Arrays.stream(row).map(
            p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
  }

  @Override
  public void cleanBoard() {
    this.board = new Player[COLUMNSIZE][ROWSIZE];
    this.turnCount = 1;
  }
}
