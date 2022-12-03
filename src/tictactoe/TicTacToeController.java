package tictactoe;


/**
 * The class representation for a TicTacToe controller
 */
public class TicTacToeController implements TicTacToeFeatures{

  private final TicTacToe model;
  private TicTacToeView view;

  /**
   * A constructor for the TicTacToe console.
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
  public void playAtPosition(int button, int row, int col) {

    try {
      model.move(row, col);
      view.setTextButton(button, "" + displayTurn());
      view.setTitleText(this.displayTurn());
    } catch (Exception e) {
//      view.displayMessage(e.getMessage());
    }

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
