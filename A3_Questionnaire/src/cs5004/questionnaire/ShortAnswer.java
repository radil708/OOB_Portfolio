package cs5004.questionnaire;

/**
 * This is a subclass of the abstract question class. This class inherits all methods from the
 * abstractQuestion class and implements the methods in the Question interface
 */
public class ShortAnswer extends AbstractQuestion {
  private String shortAnswerPrompt;
  private boolean shortAnswerRequirement;
  private String shortAnswerAnswer;

  /**
   * This constructor creates a new ShortAnswer object containing the prompt and requirement.
   *
   * @param shortAnswerPrompt      is the question's prompt as a string.
   * @param shortAnswerRequirement is the question's requirement as a boolean.
   */
  public ShortAnswer(String shortAnswerPrompt, boolean shortAnswerRequirement) {
    super(shortAnswerPrompt, shortAnswerRequirement);
    this.shortAnswerAnswer = "";
  }



  @Override
  public void answer(String userAnswer) {
    if (userAnswer == null) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
              + " or 'no' "); }
    if (userAnswer.equals("") ) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
            + " or 'no' "); }
    if (userAnswer.length() > 280) {
      throw new IllegalArgumentException("The answer cannot be greater than 128 characters");
    } else {
      this.shortAnswerAnswer = userAnswer;
    }

  }

  @Override
  public String getAnswer() {
    return this.shortAnswerAnswer;
  }

  @Override
  public Question copy() {
    ShortAnswer clone = new ShortAnswer(this.getPrompt(), this.isRequired());
    return clone;
  }


}
