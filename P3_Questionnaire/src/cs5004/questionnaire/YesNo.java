package cs5004.questionnaire;

/**
 * This is a subclass of the abstract question class. This class inherits all methods from the
 * abstractQuestion class and implements the methods in the Question interface
 */
public class YesNo extends AbstractQuestion {
  private String yesNoPrompt;
  private boolean yesNoRequirement;
  private String yesNoAnswer;

  /**
   * This constructor creates a new YesNo object containing the prompt and requirement.
   *
   * @param yesNoPrompt      is the question's prompt as a string.
   * @param yesNoRequirement is the question's requirement as a boolean.
   */
  public YesNo(String yesNoPrompt, boolean yesNoRequirement) {
    super(yesNoPrompt, yesNoRequirement);
    yesNoAnswer = "";
  }

  /**
   * Accepts yes or no as answer irregardless of case. This will throw an illegal argument if any
   * other answer is used.
   *
   * @param userAnswer The answer the user has made.
   */
  @Override
  public void answer(String userAnswer) {
    if (userAnswer == null) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
            + " or 'no' "); }
    if (userAnswer.equals("") ) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
            + " or 'no' "); }
    if (userAnswer.equalsIgnoreCase("YES")) {
      this.yesNoAnswer = "Yes";
    } else if (userAnswer.equalsIgnoreCase("NO")) {
      this.yesNoAnswer = "No";
    } else {
      throw new IllegalArgumentException("The answer can only be 'yes' or 'no' ");
    }

  }

  @Override
  public String getAnswer() {
    return this.yesNoAnswer;
  }

  @Override
  public Question copy() {
    YesNo clone = new YesNo(this.getPrompt(), this.isRequired());
    clone.answer(this.yesNoAnswer);
    return clone;
  }

}
