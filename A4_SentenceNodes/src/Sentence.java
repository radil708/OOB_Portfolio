/**
 * This interface represents a sentence.
 */
public interface Sentence {
  /**
   * This method gets the number of words
   * in a sentence. Punctuation does not count as a word.
   * @return the number of words in a sentence as an int.
   */
  int getNumberOfWords();

  /**
   * This is a helper function to getNumberOfWords.
   * @param acc an int passed in that acts as the starter. It will be 0 for the head of the node.
   *            As the recursive call is called a 1 will be added if it is a word node and none
   *            will be added if it is an empty node or punctuation node.
   * @return the current count as an integer.
   */
  int count(int acc);

  /**
   * This returns the longest word in a sentence.
   * @return the longest word as a String.
   */
  String longestWord();


  /**
   * This converts the interface Sentence Object into a String.
   * @return the sentence as a String.
   */
  String toString();

  /**
   * This returns a  duplicate sentence object without mutating the original.
   * @return a duplicate Sentence object.
   */
  Sentence clone();

  /**
   * This will combine two sentences into a sing Sentence object. The Sentence
   * object will preserve all punctuation. The original list should
   * not be changed.
   * @param other the Sentence to combine with the current Sentence object.
   * @return a Sentence object representing the two sentence objects combined.
   */
  Sentence merge(Sentence other);

  /**
   * This is a getter method for the data stored by the nodes.
   * @return data stored by the node as a string.
   */
  String getInput();
}
