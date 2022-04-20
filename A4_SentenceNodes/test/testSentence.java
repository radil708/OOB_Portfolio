import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * THis is a JUNit test for the Sentence Interface objects.
 */
public class testSentence {
  private WordNode word1;
  private Sentence end;
  private Sentence wordAa;
  private Sentence wordB;

  @Before
  public void setUp() {
    end = new EmptyNode();
    //Sentence punc3 = new PunctuationNode("!", end);
    Sentence word5 = new WordNode("friend", end);
    Sentence word4 = new WordNode("old", word5);
    Sentence word3 = new WordNode("my", word4);
    Sentence punc2 = new PunctuationNode(":", word3);
    Sentence word2 = new WordNode("Darkness", punc2);
    Sentence punc1 = new PunctuationNode(",", word2);
    word1 = new WordNode("Hello", punc1);
    Sentence puncE = new PunctuationNode("?", end);
    Sentence wordD = new WordNode("right", puncE);
    Sentence puncC = new PunctuationNode("," , wordD);
    wordB = new WordNode("okay", puncC);
    Sentence wordAc = new WordNode("s", wordB);
    Sentence puncAb = new PunctuationNode("'", wordAc);
    wordAa = new WordNode("It", puncAb);

  }

  @Test
  public void testCount() {
    // counts are correct when using punctuations
    assertEquals(5, word1.getNumberOfWords() );
    assertEquals(2, wordB.getNumberOfWords());
  }

  @Test
  public void testToString() {
    // Here we see that a period has been added to a sentence when it does not end in a punctuation
    assertEquals("Hello, Darkness: my old friend.", word1.toString());
    // Here we see no period has been added to a sentence that already ends in a punctuation
    assertEquals("It's okay, right?", wordAa.toString());
  }

  @Test
  public void TestLongestWord() {
    // longest word is in the middle after a punctuation
    assertEquals("Darkness", word1.longestWord());
    Sentence testTail = new WordNode("two", end);
    Sentence testMid = new PunctuationNode(",", testTail);
    Sentence testHead = new WordNode("Thirty", testMid);
    // longest word is the first word before a punctuation
    assertEquals("Thirty", testHead.longestWord());
    Sentence testTail1 = new WordNode("word", end);
    Sentence testMid1 = new WordNode("longest", testTail1);
    Sentence testHead1 = new WordNode("This", testMid1);
    // longest word when no punctuation present
    assertEquals("longest", testHead1.longestWord() );
    assertEquals("right", wordAa.longestWord());
  }

  @Test
  public void testClone() {
    Sentence cloneA = wordAa.clone();
    // this shows these are not the same object
    assertNotEquals(wordAa.hashCode(), cloneA.hashCode());
    // but still have the same content
    assertEquals("It's okay, right?", cloneA.toString());
  }

  @Test
  public void testMerge() {
    Sentence merged = wordAa.merge(word1);
    assertEquals("It's okay, right? Hello, Darkness: my old friend.", merged.toString());
    // Here we show that the original sentences have not been mutated
    assertEquals("It's okay, right?", wordAa.toString());
    assertEquals("Hello, Darkness: my old friend.", word1.toString());
  }
}
