package tictactoe;

public class Controller implements Features {
  private IModel model;
  private IView view;
	
  public Controller(IModel m) {
    model = m;
  }

  public void setView(IView v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }


  @Override
  public void toggleColor() {
    view.toggleColor();
  }

  @Override
  public void makeUppercase() {
    String text = model.getString();
    text = text.toUpperCase();
    view.setEchoOutput(text);
  }

  @Override
  public void restoreLowercase() {
    String text = model.getString();
    view.setEchoOutput(text);
  }

  @Override public void invertString() {
    String text = model.invertString();
    view.setEchoOutput(text);
  }

  @Override public void removeVowels() {
    String text = model.removeVowels();
    view.setEchoOutput(text);
  }

  @Override
  public void echoOutput(String typed) {
    //send text to the model
    model.setString(typed);

    //clear input textfield
    view.clearInputString();
    //finally echo the string in view
    String text = model.getString();
    view.setEchoOutput(text);

    //set focus back to main frame so that keyboard events work
    view.resetFocus();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void playAtPosition() {

  }

  @Override
  public void restartGame() {

  }

  @Override
  public void displayTurn() {

  }

  @Override
  public void playGame(TicTacToe m) {

  }
}
