package tictactoe;

import javax.swing.*;
import java.awt.*;
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
   * A constructor for the tictactoe console. The console is comprised of TicTacToe model
   *
   * @param model the model we are connecting to the controller, of type TicTacToe
   */
  public TicTacToeController(TicTacToe model) {
    if (model == null) {
      throw new IllegalArgumentException("Invalid input target, cannot be null");
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

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public String playAtPosition(int button, int row, int col) {
    String temp = "" + displayTurn();
    try {
      model.move(row, col);
      view.setTextButton(button, temp);
      view.setTitleText(this.displayTurn());
    } catch (Exception e) {
//      view.displayMessage(e.getMessage());
    }

    return temp;
  }

  @Override
  public boolean isGameOver(){
    return model.isGameOver();
  }
  @Override
  public Player displayWinner(){
    return model.getWinner();
  }

  @Override
  public void restartGame() {

    //view cleaning
    view.cleanBoard();

    //Model cleaning
    model.cleanBoard();
    //Reset Title
    view.setTitleText(Player.X);
  }

  @Override
  public Player displayTurn() {
    //send text to the model
    return model.getTurn();
  }
}
