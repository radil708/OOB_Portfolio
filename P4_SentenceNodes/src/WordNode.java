/**
 * This represents a word in a sentence and implements the
 * Sentence interface.
 */
public class WordNode implements Sentence {
  private String input;
  private Sentence rest;

  /**
   * Constructor for WordNode. An exception will be thrown if the wordInput parameter is null, an
   * empty string or a space " " .
   *
   * @param wordInput the word the object is storing as a String.
   * @param pointer   the Sentence object the "rest" attribute is pointing to.
   */
  public WordNode(String wordInput, Sentence pointer) {
    if (wordInput == null || wordInput.equals("") || wordInput.equals(" ")) {
      throw new IllegalArgumentException("The word cannot be null, empty string or empty space");
    }
    if (isPuncInWord(wordInput)) {
      throw new IllegalArgumentException("There is a punctuation in this word, punctuations should"
              + "only be in a punctuation node.");
    }
    this.input = wordInput;
    this.rest = pointer;
  }

  @Override
  public String getInput() {
    return this.input;
  }

  /**
   * This is a helper function for the isPuncInWord method. It will analyze
   * if a string contains a punctuation element.
   * @param stringToStore string to analyze as a single character.
   * @return true if the parameter is a punctuation element.
   */
  public boolean isPunctuation(String stringToStore) {
    return (stringToStore.equals(".") || stringToStore.equals(",") || stringToStore.equals("?")
            || stringToStore.equals("!") || stringToStore.equals("'") || stringToStore.equals(":")
            || stringToStore.equals(";"));
  }

  /**
   * This loops through all the characters of a word to see if a string element contains
   * a punctuation element.
   * @param word the word to be analyzed as a String.
   * @return true if a punctuation element is present in the word parameter.
   */
  private boolean isPuncInWord(String word) {
    // loop through all the characters in a word and check if there is a punctuation
    for (int i = 0; i < word.length(); i++) {
      String c = String.valueOf(word.charAt(i));
      if (isPunctuation(c)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int getNumberOfWords() {
    // Seed value needs to be 0 because count method adds 1 for the current word
    return this.count(0);
  }


  @Override
  public int count(int acc) {
    // Will point the count method to the next Sentence object and will add 1 indicating it has
    //been counted.
    return this.rest.count(acc + 1);
  }

  @Override
  public String longestWord() {
    if ( this.rest.longestWord().length() > this.getInput().length() ) {
      return this.rest.longestWord();
    }
    else {
      return this.getInput();
    }
  }


  @Override
  public Sentence clone() {
    Sentence clone = new WordNode(this.getInput(), this.rest.clone());
    return clone;
  }

  @Override
  public Sentence merge(Sentence other) {
    Sentence mergeSentence = new WordNode(this.getInput(), this.rest.merge(other));
    return mergeSentence;
  }


  @Override
  public String toString() {
    // if the node after the last word is empty append a period
    if (this.rest.toString().equals("")) {
      return this.getInput() + ".";
    }
    //if the next node is punctuation do not add space before it
    else if (isPunctuation(this.rest.getInput())) {
      return this.getInput() + this.rest.toString();
    }
    return this.getInput() + " " + this.rest.toString();
  }
}
