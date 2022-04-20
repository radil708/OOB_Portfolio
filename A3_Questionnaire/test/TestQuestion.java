import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;


import cs5004.questionnaire.Likert;
import cs5004.questionnaire.Question;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

/**
 * This is a JUnit test for the Question Interface.
 */
public class TestQuestion {
  private Question yesNoQuestion1;
  private Question yesNoQuestion2;
  private Question yesNoQuestion3;
  private String yesNoPrompt;
  private Question sA1;
  private Question sA2;
  private String sAPrompt;
  private String greaterThan280;
  private Question likert1;
  private Question likert2;
  private String likertPrompt1;
  private String likertPrompt2;

  @Before
  public void setUp() {
    // Set up for YesNo Questions
    yesNoPrompt = "Do you like green eggs and ham?";
    yesNoQuestion1 = new YesNo(yesNoPrompt,true);
    yesNoQuestion1.answer("yEs");
    yesNoQuestion2 = new YesNo(yesNoPrompt,false);
    yesNoQuestion3 = new YesNo(yesNoPrompt, true);
    yesNoQuestion3.answer("nO");

    //Set up for short answer question
    sAPrompt = "In less than 128 characters, please answer the question: "
            + "What movies have you seen?";
    sA1 = new ShortAnswer(sAPrompt, true);
    sA2 = new ShortAnswer(sAPrompt, false);
    greaterThan280 = "Interstellar, The Star Wars Holiday Special (I TRULY REGRET "
            + "watching this movie), Leprechaun 4: in space (I did NOT regret watching "
            + "this movie), Forrest Gump, Avengers: Infinity War, Avengers: Endgame, "
            + "Amelie, the 400 blows, Citizen Kane, As Above So Below, Mulholland Drive, "
            + "THX1138, Sound of Metal, Nomad land, Paddington 2, Earwig and the witch";

    //Set up for Likert questions
    likertPrompt1 = "What is your stance on the idea that 'cats are great pets!'";
    likert1 = new Likert(likertPrompt1,true);
    likertPrompt2 = "Do you agree or disagree that Interstellar was a great movie!";
    likert2 = new Likert(likertPrompt2, false);
  }

  @Test
  // Testing constructor for yes or no questions.
  public void testYesNoConstructor() {
    assertEquals(yesNoQuestion1.getPrompt(), yesNoPrompt); //Testing question prompt
    assertTrue(yesNoQuestion3.isRequired()); // Testing true for requirement
    assertFalse(yesNoQuestion2.isRequired()); // Testing False for requirement
    assertEquals(yesNoQuestion2.getAnswer(), ""); // Testing for no answer as empty string
  }

  @Test
  public void testYesNoAnswer() {
    assertEquals("Yes",yesNoQuestion1.getAnswer() ); // Testing yes as answer for YesNo question.
    assertEquals("No", yesNoQuestion3.getAnswer());// Testing No as answer for YesNo question.
  }

  @Test
  public void testShortAnswerConstructor() {
    assertEquals(sA1.getPrompt(), sAPrompt); //Testing question prompt
    assertTrue(sA1.isRequired()); // Testing true for requirement
    assertFalse(sA2.isRequired()); // Testing False for requirement
  }

  @Test
  public void testShortAnswerAnswer() {
    //Testing if answer is the same if character total is less than 128
    String lessThan280 = "Interstellar, The Star Wars Holiday Special (I TRULY REGRET "
            + "watching this movie), Leprechaun 4: In Space (I did NOT regret watching this movie),"
            + " Forrest Gump, Avengers: Infinity War, Avengers: Endgame";
    sA1.answer(lessThan280);
    assertEquals(sA1.getAnswer(), lessThan280);
  }

  @Test
  // Testing Constructor for Likert questions.
  public void testLikertConstructor() {
    assertEquals(likert1.getPrompt(), likertPrompt1); //Testing question prompt
    assertEquals(likert2.getPrompt(), likertPrompt2); //Testing question prompt
    assertTrue(likert1.isRequired()); //Testing true for requirement
    assertFalse(likert2.isRequired()); //Testing true for requirement
    assertEquals(likert1.getAnswer(), ""); // Testing for no answer as empty string
  }

  @Test
  public void testLikertResponse() {
    assertEquals(likert1.getAnswer(), "");
    //testing for empty answerlikert1.answer("StrOngLy AgrEe");
    likert1.answer("StRonGLy AgREe");
    assertEquals(likert1.getAnswer(), "Strongly agree");
    likert2.answer("aGreE");
    assertEquals(likert2.getAnswer(), "Agree");
    likert1.answer("Neutral");
    assertEquals(likert1.getAnswer(),"Neither Agree Nor Disagree");
    likert2.answer("NeiTheR AGreE nOr DisAgRee");
    assertEquals(likert2.getAnswer(),"Neither Agree Nor Disagree" );
    likert1.answer("DIsaGrEE");
    assertEquals(likert1.getAnswer(), "Disagree");
    likert2.answer("StrONglY dIsAgREe");
    assertEquals(likert2.getAnswer(), "Strongly Disagree");

  }


  @Test (expected = IllegalArgumentException.class)
  public void testIllegalArgument() {
    // Testing if response if neither yes or no for yes or no question
    yesNoQuestion3.answer("maybe");
    // Testing if response > 280 char for short answer question
    sA2.answer(greaterThan280);
    // Testing if response is NOT Strongly Agree, Agree, Neither Agree nor Disagree,
    //    Disagree, or Strongly Disagree
    likert1.answer("I don't feel strongly either way");
    likert2.answer("10");
    //Testing if prompt is an empty string or if answer is a null.
    yesNoQuestion3.answer("");
    sA2.answer(null);
    Question emptyYesNO = new YesNo("", false);
    Question emptyLikert = new Likert(null, true);
  }

  @Test
  public void testCopyAndEquals() {

    Question yesNoClone1 = yesNoQuestion1.copy();
    Question yesNoQuestionCustom = new YesNo("Do you like ice cream?", true);
    //Testing to see if the a copy of a YesNo question is equal to the original - it should be.
    assertEquals(yesNoQuestion1.toString(), yesNoClone1.toString());
    // Testing to see if two questions of the same YesNo class but have different prompts are
    // different - it should be.
    assertNotEquals(yesNoQuestion2.toString(), yesNoQuestionCustom.toString());
    Question sAClone1 = sA1.copy();
    // Testing to see if a copy of a short answer question is equal to the original - it should be.
    assertEquals(sA1.toString(),sAClone1.toString());
    // Testing to see if two questions of different classes are the same - it should not be.
    assertNotEquals(sA1.toString(), yesNoClone1.toString());
    //Testing to see if the a copy of a Likert question is equal to the original - it should be.
    Question likertClone1 = likert1.copy();
    assertEquals(likertClone1.toString(), likert1.toString());
    // Testing to see if two questions of the same likertClone class but have different prompts are
    // different - it should be.
    String newLikertPrompt = "Write an essay in which you explain how Bobby Braun builds an "
            + "argument to persuade his audience that the US government must continue"
            + " to invest in NASA.";
    Question notLikertClone = new Likert(newLikertPrompt, true);
    assertFalse(likertClone1.equals(notLikertClone));
    // More testing to see if two questions of different classes are the same
    Question notLikertClone2 = new Likert(sAClone1.getPrompt(), true);
    assertFalse(notLikertClone2.equals(sAClone1));




  }
}
