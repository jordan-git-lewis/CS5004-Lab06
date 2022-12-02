package tictactoe;

/**
 * This is the Model Interface...
 * New version by Lino.
 */
public interface IModel
{
  /**
   * Set a new String.
   * @param i the String we want to use
   */
  void setString(String i);

  /**
   * Get the String.
   *
   * @return the Echoed String
   */
  String getString();

  /**
   * Invert the order of the characters in a String.
   * Example: "I am Lino"  changes to "onil ma I".
   *
   * @return the inverted String.
   */
  String invertString();

  /**
   * Remove the vowels (a, e, i, o , u) from the String.
   *
   * @return the String without the vowels.
   */
  String removeVowels();
}
