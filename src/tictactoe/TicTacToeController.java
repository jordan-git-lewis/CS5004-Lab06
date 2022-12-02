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
   * @param model  the model we are connecting to the controller, of type TicTacToe
   */
  public TicTacToeController(TicTacToe model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model input, cannot be null");
    }
    this.model = model;
  }

  /**
   * Sets the view and adds the features from the controller/TicTacToe Features interface
   * @param v the view to implement
   */
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
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public String playAtPosition(int button, int row, int col) {
    try {
      String temp = "" + displayTurn();
      model.move(row, col);
      view.setTextButton(button, temp);
      view.setTitleText(this.displayTurn());
      return temp;
    }catch (Exception e){
      return null;
    }
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
