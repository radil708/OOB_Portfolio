import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import cs5004.questionnaire.Likert;
import cs5004.questionnaire.Question;
import cs5004.questionnaire.Questionnaire;
import cs5004.questionnaire.QuestionnaireImpl;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

/**
 * This is a JUnit test for the Questionnaire interface and the QuestionnaireImpl class.
 * There is a test for every method of the Questionnaire interface.
 * Some tests, test multiple methods.
 */
public class TestQuestionnaire {
  private Questionnaire qnaire1;
  private Question yesNoQuestion1;
  private Question sA1;
  private Question likert1;
  private Questionnaire qnaire1Clone;

  //Test exemptions including "" for any questions and answer
  //Trying to answer with empty string
  //Try to answer null

  @Before
  public void setUp() {
    String yesNoPrompt = "Do you like green eggs and ham?";
    yesNoQuestion1 = new YesNo(yesNoPrompt,true);
    String sAPrompt = "In less than 128 characters, please answer the question: "
            + "What movies have you seen?";
    sA1 = new ShortAnswer(sAPrompt, false);
    sA1.answer("Spiderman");
    String likertPrompt1 = "What is your stance on the idea that 'cats are great pets!'";
    likert1 = new Likert(likertPrompt1,true);
    qnaire1 = new QuestionnaireImpl();
    qnaire1.addQuestion("Q1", yesNoQuestion1);
    qnaire1.addQuestion("Q2", sA1);
    qnaire1.addQuestion("Q3", likert1);
    qnaire1Clone = new QuestionnaireImpl();
    qnaire1Clone.addQuestion("Q1", yesNoQuestion1);
    qnaire1Clone.addQuestion("Q2", sA1);
    qnaire1Clone.addQuestion("Q3", likert1);
  }

  @Test
  public void testQuestionnaireConstructorAndFoldAndSort() {
    //This also tests add question, fold method, and toString method.
    //becuase the toString method depends on fold.
    assertEquals("Question: What is your "
            + "stance on the idea that 'cats are great pets!'\n"
            + "\n" + "Answer: \n" + "\n" + "Question: In less than 128 characters, "
            + "please answer the question: What movies have you seen?\n"
            + "\n" + "Answer: Spiderman\n" + "\n" + "Question: Do you like" +
            " green eggs and ham?\n"
            + "\n" + "Answer:", qnaire1.toString());
  }

  @Test
  public void testRemoveQuestion() {
    qnaire1Clone.removeQuestion("Q3");
    assertNotEquals(qnaire1.toString(), qnaire1Clone.toString());
  }

  @Test
  public void testGetQuestion() {
    Question extractedQuestion = qnaire1.getQuestion("Q3");
    assertEquals(extractedQuestion.toString(), likert1.toString());
    assertNotEquals(extractedQuestion.toString(), yesNoQuestion1.toString());
    Question extractIntQuestion = qnaire1.getQuestion(1);
    assertEquals(sA1.toString(), extractIntQuestion.toString());

  }

  @Test
  public void testGetRequiredAndOptionalQuestion() {
    List<Question> requiredListQnair1 = qnaire1.getRequiredQuestions();
    assertEquals("[Question: Do you like green eggs and ham?\n"
            + "Answer: , Question: What is your stance on the idea that 'cats are great pets!'\n"
            + "Answer: ]", requiredListQnair1.toString());
    List<Question> optionalListQnair1 = qnaire1.getOptionalQuestions();
    assertEquals("[Question: In less than 128 characters, please answer"
            + " the question: What movies have you seen?\n"
            + "Answer: Spiderman]", optionalListQnair1.toString());
  }

  @Test
  public void testIsComplete() {
    System.out.println(qnaire1.toString());
    assertFalse(qnaire1.isComplete());
    QuestionnaireImpl qnaireComplete = new QuestionnaireImpl();
    Question q2ShortAnswer = new ShortAnswer("Will you pass "
            + "this class", true);
    q2ShortAnswer.answer("I hope so ");
    Question q2Likert = new Likert("Do you think this"
            + " code will get a passing grade?", true);
    q2Likert.answer("neutral");
    qnaireComplete.addQuestion("Q1",q2ShortAnswer);
    qnaireComplete.addQuestion("Q2",q2Likert);
    assertTrue(qnaireComplete.isComplete());
  }

  @Test
  public void testGetResponses() {
    List<String> qnaire1Responses = qnaire1.getResponses();
    assertEquals("[, Spiderman, ]", qnaire1Responses.toString());
  }

  @Test
  public void testFilter() {
    Question newQuestion = new YesNo("Do you hope to pass this "
            + "class?", true);
    newQuestion.answer("yes");
    yesNoQuestion1.answer("no");
    qnaire1.addQuestion("Q4", newQuestion );
    Questionnaire qnaireYesNoOnly = qnaire1.filter(q -> q instanceof YesNo) ;
    Questionnaire compareQnaire = new QuestionnaireImpl();
    compareQnaire.addQuestion("Q1", yesNoQuestion1);
    compareQnaire.addQuestion("Q4", newQuestion);
    //ignore the print statements they are only there for me to see the to String
    System.out.println(qnaireYesNoOnly.toString());
    System.out.println("--------compar--------");
    System.out.println(compareQnaire.toString());
    assertEquals(qnaireYesNoOnly.toString(), compareQnaire.toString());
  }

  @Test
  public void testComparatorAndSort() {
    //If object 1 is bigger than object 2 then it will be higher priority
    qnaire1.sort((obj1, obj2) -> obj1.getPrompt().length() - (obj2.getPrompt().length()));
    assertEquals("Question: In less than 128 characters, please answer"
            + " the question: What movies have you seen?\n"
            + "\n" + "Answer: Spiderman\n" + "\n" + "Question: What is your "
            + "stance on the idea that 'cats are great pets!'\n" + "\n"
            + "Answer: \n" + "\n" + "Question: Do you like green eggs and ham?\n"
            + "\n" + "Answer:", qnaire1.toString());
  }

  @Test (expected = NoSuchElementException.class)
  public void testNoSuchElement() {
    qnaire1.removeQuestion("Question 5006");
    qnaire1.getQuestion("Question 7789");
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testIndexOutOfBounds() {
    qnaire1.getQuestion(10);
  }

}

