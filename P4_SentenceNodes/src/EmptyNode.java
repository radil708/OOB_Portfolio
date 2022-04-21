/**
 * This represents the end of a sentence. It implements
 * the Sentence interface.
 */
public class EmptyNode implements Sentence {
  @Override
  public int getNumberOfWords() {
    // This will do anything because getNumber of words
    // will never be called by an EmptyNode
    return 0;
  }

  @Override
  public int count(int acc) {
    // at end of sentence return accumulator
    return acc;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public Sentence clone() {
    return this;
  }

  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  @Override
  public String getInput() {
    return "";
  }

  @Override
  public String toString() {
    return "";
  }
}
