/**
 * This represents a punctuation in a sentence.
 */
public class PunctuationNode implements Sentence {
  private String input;
  private Sentence rest;

  /**
   * This is the constructor for the punctuation node. I twill throw Illegal Argument exceptions
   * if the punctuation to input is not a punctuation element.
   * @param punctuationInput the punctuation to put in the node.
   * @param pointer the next word or punctuation in the sentence.
   */
  public PunctuationNode(String punctuationInput, Sentence pointer) {
    if (punctuationInput == null || punctuationInput.equals("") || punctuationInput.equals(" ")) {
      throw new IllegalArgumentException("The word cannot be null, empty string or empty space");
    }
    if (!isPunctuation(punctuationInput)) {
      throw new IllegalArgumentException("a punctuation node can only store the following"
              + "punctuations: . or : or , or ; or ? or !");
    }
    this.input = punctuationInput;
    this.rest = pointer;
  }

  /**
   * This is a helper function for the constructor. It will analyze
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
   * This is a helper method for tne longest word method in the abstract class.
   * This is needed so punctuation are not taken into account when finding the longest word.
   * @param prevWord the previous word in the sentence (i.e. the data in the node before the
   *                 punctuation node.)
   * @return a String representing the longest word.
   */
  public String helper(String prevWord) {
    if (prevWord.length() > this.rest.getInput().length()) {
      return prevWord;
    }
    return this.rest.longestWord();
  }

  @Override
  public Sentence clone() {
    Sentence clone = new PunctuationNode(this.getInput(), this.rest.clone());
    return clone;
  }

  @Override
  public String longestWord() {
    return this.rest.longestWord();
  }

  @Override
  public Sentence merge(Sentence other) {
    Sentence mergeSentence = new PunctuationNode(this.getInput(), this.rest.merge(other));
    return mergeSentence;
  }

  @Override
  public String getInput() {
    return this.input;
  }


  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public int count(int acc) {
    // Will point the count method to the next Sentence object and will not add anything
    // because a punctuation is not a word
    return this.rest.count(acc);
  }

  @Override
  public String toString() {
    // if apostrophe is used
    if (this.getInput().equals("'")) {
      return this.getInput() + this.rest.toString();

    }
    // no periods added if punctuation is at end of sentence
    if (this.rest.toString().equals("")) {
      return this.getInput();
    }
    return this.getInput() + " " + this.rest.toString();
  }


}

