package cs5004.questionnaire;

/**
 * This is an enum used to get the answer for the Likert class.
 */
enum LikertResponseOption {
  STRONGLY_DISAGREE(-2, "Strongly Disagree"),
  DISAGREE(-1, "Disagree"),
  NEUTRAL(0, "Neither Agree Nor Disagree"),
  AGREE(1, "Agree"),
  STRONGLY_AGREE(2, "Strongly agree");

  private final int val;
  private final String txt;

  LikertResponseOption(int val, String txt) {
    this.val = val;
    this.txt = txt;
  }

  int getValue() {
    return val;
  }

  String getText() {
    return txt;
  }
}
