package cs5004.questionnaire;

/**
 * This is the superclass of all the other question classes.
 */
public abstract class AbstractQuestion implements Question {
  private String prompt;
  private boolean requirement;

  /**
   * This is the constructor for the AbstractQuestion Class.
   * @param prompt the actual question as a string
   * @param requirement a boolean value stating if a question is required or not,
   *                    true if it is, false otherwise.
   */
  public AbstractQuestion(String prompt, boolean requirement) {
    if ( prompt == null ) {
      throw new IllegalArgumentException("The prompt"
            + "of the question cannot be null"); }
    if ( prompt == null ) {
      throw new IllegalArgumentException("The prompt"
              + "of the question cannot be null"); }
    this.prompt = prompt;
    this.requirement = requirement;
  }

  /**
   * Gets the question of the current question object.
   * @return the question of the object as a string
   */
  @Override
  public String getPrompt() {
    return this.prompt;
  }

  /**
   * Gets the requirement of the question as a question object.
   * @return the requirement as a boolean value; true is required, false if not.
   */
  @Override
  public boolean isRequired() {
    return this.requirement;
  }

  @Override
  public String toString() {
    String str = "Question: " + this.getPrompt() + "\n" + "Answer: " + this.getAnswer() ;
    return str;
  }


}
