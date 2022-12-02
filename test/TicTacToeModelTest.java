import org.junit.Test;

import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the tic tac toe model. Verifying that game state is properly managed, and all game
 * actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1 = new TicTacToeModel();

  /**
   * Test that the game starts with player X
   */
  @Test
  public void testStartingPlayer() {
    assertEquals(Player.X, ttt1.getTurn());
  }

  /**
   * Tests the move() function allows legal moves and asserts which players turn it is
   */
  @Test
  public void testMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());

    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
  }

  /**
   * Tests that at the start of a game of tic tac toe, all board positions are null
   */
  @Test
  public void testEmptyBoard() {
    Player[][] newBoard = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertNull(newBoard[i][j]);
      }
    }
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", ttt1.toString());
  }

  /**
   * tests a scenario where a player wins with a horizontal row
   */
  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());

    assertEquals(" X | X | X\n"
        + "-----------\n"
        + " O |   |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  /**
   * Tests a scenario where a player wins vertically
   */
  @Test
  public void testVerticalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 1); // O takes middle
    ttt1.move(1, 0); // X takes left middle
    assertNull(ttt1.getWinner());
    ttt1.move(0, 2); // O takes Upper right
    ttt1.move(2, 0); // X takes lower right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());

    assertEquals(" X |   | O\n"
        + "-----------\n"
        + " X | O |  \n"
        + "-----------\n"
        + " X |   |  ", ttt1.toString());
  }

  /**
   * Tests a scenario where a player wins diagonally
   */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
        + "-----------\n"
        + " X | O |  \n"
        + "-----------\n"
        + " O |   |  ", ttt1.toString());
  }

  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }

  /**
   * Test the gameOver() method returns false if the game is not over
   * and true if the game is over
   */
  @Test
  public void testGameOver(){
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
  }

  /**
   * Tests an invalid move
   */
  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      //assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  /**
   * Test that a player cannot make a move after the game is over
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }

  /**
   * Test a game scenario where the board is full and there is no winner
   */
  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals(" X | O | X\n"
        + "-----------\n"
        + " O | O | X\n"
        + "-----------\n"
        + " X | X | O", ttt1.toString());
  }

  /**
   * Tests that the getMarkAt() function returns the correct Player enum
   */
  @Test
  public void testGetMarkAt() {
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    assertEquals(Player.X, ttt1.getMarkAt(0, 2));
    assertEquals(Player.O, ttt1.getMarkAt(0, 1));
    assertEquals(Player.X, ttt1.getMarkAt(2, 1));
  }

  /**
   * Tests that getMarkAt throws an IllegalArgumentException if the user inputs an invalid row
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }

  /**
   * Tests that getMarkAt throws an IllegalArgumentException if the user inputs an invalid column
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }

  /**
   * Tests the getBoard method
   */
  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  // TODO: test case where board is full AND there is a winner

  /**
   * Tests a scenario where the board is full and there is a winner
   */
  @Test
  public void testFullBoardWinner() {
    ttt1.move(1, 1); // X goes middle
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(0, 2); // O goes upper right
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(2, 2); // X goes lower right
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(0, 0); // O goes upper left
    assertEquals(Player.X, ttt1.getTurn());
    assertNull(ttt1.getWinner());
    ttt1.move(0, 1); // X goes upper middle
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 0); // O goes middle left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // X goes lower left
    ttt1.move(1, 2); // O goes middle right
    ttt1.move(2, 1); // X goes lower middle
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" O | X | O\n"
        + "-----------\n"
        + " O | X | O\n"
        + "-----------\n"
        + " X | X | X", ttt1.toString());
  }
}
