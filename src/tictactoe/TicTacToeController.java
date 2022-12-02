package tictactoe;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The class representation for a tictactoe controller
 */
public class TicTacToeController implements TicTacToeFeatures{

  private TicTacToe model;
  private TicTacToeView view;

  /**
   * A constructor for the tictactoe console. The console is comprised of a Readable input and the
   * appendable output for testing
   *
   * @param model  the read in input
   */
  public TicTacToeController(TicTacToe model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid input target, cannot be null");
    }
    this.model = model;
  }

  public void setView(TicTacToeView v){
    view = v;
    view.addFeatures(this);
  }


  /**
   * Execute a single game of tic tac toe given a tic tac toe Model. When the game is over, the
   * playGame method ends.
   *
   */
  @Override
  public void playGame() {

  }

  @Override
  public void echoOutput(String typed) {

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public String playAtPosition(int button, int row, int col) {
    String temp = "" + displayTurn();
    model.move(row, col);
    view.setTextButton(button, temp);
    view.setTitleText(this.displayTurn());
    return temp;
  }

  @Override
  public void restartGame() {

  }

  @Override
  public Player displayTurn() {
    //send text to the model
    return model.getTurn();
  }
}
