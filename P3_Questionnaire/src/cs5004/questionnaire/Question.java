package cs5004.questionnaire;

/**
 * This is is the Question Interface. It contains all methods that are mandated in all questionnaire
 * classes.
 */
public interface Question {
  /**
   * This will return the question of the text.
   * @return The question of the text as a string.
   */
  String getPrompt();

  /**
   * States whether the question is required or not.
   * @return a boolean depending on if a question is required or not.
   */
  boolean isRequired();

  /**
   * This is the answer a person is making for the question.
   * What the parameter contains depends on the question.
   * Does this need a prompt or is the parameter the answer?
   * @param userAnswer The answer the user has made.
   */
  void answer(String userAnswer);

  /**
   * This will return the answer to the question.
   * @return the answer to the question as a string.
   */
  String getAnswer();

  /**
   * This will return a copy of the current question object
   * including all of its data.
   * @return a copy of the question object as a Question Interface object.
   */
  Question copy();
}
