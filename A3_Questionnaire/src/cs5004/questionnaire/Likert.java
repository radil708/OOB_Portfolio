package cs5004.questionnaire;


/**
 * This is a subclass of the abstract question class. This class inherits all methods from
 * the abstractQuestion class and implements the methods in the Question interface
 */
public class Likert extends AbstractQuestion {
  private String likertPrompt;
  private boolean likertRequirement;
  private String likertResponseAnswer;
  private LikertResponseOption likertResponse;

  /**
   * This constructor creates a new Likert object containing the prompt and requirement.
   * @param likertPrompt is the question's prompt as a string.
   * @param likertRequirement is the question's requirement as a boolean.
   */
  public Likert(String likertPrompt, boolean likertRequirement) {
    super(likertPrompt, likertRequirement);
    this.likertResponseAnswer = "";
  }


  @Override
  public void answer(String userAnswer) {
    if (userAnswer == null) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
            + " or 'no' "); }
    if ( userAnswer.equals("") ) {
      throw new IllegalArgumentException("The answer can only be 'yes'"
            + " or 'no' "); }
    String warning;
    warning = "The answer must be of the following options: "
            + "Strongly Agree, Agree, Neither Agree nor Disagree / Neutral,"
            + " Disagree, or Strongly Disagree";
    if (userAnswer.equalsIgnoreCase("Strongly Agree")) {
      this.likertResponseAnswer = "FlagCorrect";
      this.likertResponse = LikertResponseOption.STRONGLY_AGREE;
    } else if (userAnswer.equalsIgnoreCase("Agree")) {
      this.likertResponseAnswer = "FlagCorrect";
      this.likertResponse = LikertResponseOption.AGREE;
    } else if (userAnswer.equalsIgnoreCase("Neither Agree nor Disagree")
            || userAnswer.equalsIgnoreCase("Neutral")) {
      this.likertResponseAnswer = "FlagCorrect";
      this.likertResponse = LikertResponseOption.NEUTRAL;
    } else if (userAnswer.equalsIgnoreCase("Disagree")) {
      this.likertResponseAnswer = "FlagCorrect";
      this.likertResponse = LikertResponseOption.DISAGREE;
    } else if (userAnswer.equalsIgnoreCase("Strongly Disagree")) {
      this.likertResponseAnswer = "FlagCorrect";
      this.likertResponse = LikertResponseOption.STRONGLY_DISAGREE;
    } else {
      throw new IllegalArgumentException(warning);
    }


  }

  @Override
  public String getAnswer() {
    if (this.likertResponseAnswer.equals("FlagCorrect")) {
      return this.likertResponse.getText(); }
    else {
      return this.likertResponseAnswer;
    }
  }

  @Override
  public Question copy() {
    Likert clone = new Likert(this.getPrompt(), this.isRequired());
    return clone;
  }

}
