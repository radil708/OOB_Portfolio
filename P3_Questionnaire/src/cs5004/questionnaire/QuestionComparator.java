package cs5004.questionnaire;

import java.util.Comparator;

/**
 * This is a custom comparator class used for the QuestionnaireImpl method: toString. It will
 * order the questions alphabetically in descending order from A to Z.
 */
public class QuestionComparator implements Comparator<Question> {
  @Override
  public int compare(Question o1, Question o2) {
    return o2.getPrompt().compareTo(o1.getPrompt());
  }
}
