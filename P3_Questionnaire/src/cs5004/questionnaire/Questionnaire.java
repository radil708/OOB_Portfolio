package cs5004.questionnaire;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Represents a collection of {@link Question}s, forming a questionnaire.
 */
public interface Questionnaire {

  /**
   * Add a question to the questionnaire.
   *
   * @param identifier a name for the question <b>unique</b> within this questionnaire. Not null
   *                   or empty.
   * @param q the {@link Question} to be added to the questionnaire
   */
  void addQuestion(String identifier, Question q);

  /**
   * Remove the question with the given identifier from the questionnaire.
   *
   * @param identifier the identifier of the question to be removed.
   * @throws NoSuchElementException if there is no question with the given identifier.
   */
  void removeQuestion(String identifier);

  /**
   * Get the question with the given number, based on the order in which it was added to the
   * questionnaire, or the sorted order if the {@code sort()} method is called. The first question
   * is 1, second 2, etc.
   *
   * @param num the number of the question, counting from 1
   * @return the question
   * @throws IndexOutOfBoundsException if there is no such question num
   */
  Question getQuestion(int num);

  /**
   * Get the question with the given identifier (question having been previously added to the
   * questionnaire).
   *
   * @param identifier the identifier of the question
   * @return the question
   * @throws NoSuchElementException if there is no question with the identifier
   */
  Question getQuestion(String identifier);

  /**
   * Return a list of all required questions in the questionnaire.
   *
   * @return the required questions.
   */
  List<Question> getRequiredQuestions();

  /**
   * Return a list of all optional questions in the questionnaire.
   *
   * @return the optional questions.
   */
  List<Question> getOptionalQuestions();

  /**
   * Report if all required questions have some non-empty response.
   *
   * @return true if all required questions have responses, false otherwise.
   */
  boolean isComplete();

  /**
   * Return a list of just the responses to all the questions in the questionnaire.
   *
   * @return the responses
   */
  List<String> getResponses();

  /**
   * Produce a new questionnaire containing just the questions where the given predicate returns
   * true. The returned questionnaire is completely independent of this questionnaire. That is,
   * the questions in the returned questionnaire are <b>copies</b> of the original questions.
   *
   * @param pq the predicate
   * @return the new questionnaire
   */
  Questionnaire filter(Predicate<Question> pq);

  /**
   * Sort the questions according to the given comparator. Return values from
   * {@code getQuestion(int)} should reflect the new sorted order following sort.
   *
   * @param comp a comparator for Question
   */
  void sort(Comparator<Question> comp);

  /**
   * Produce a single summary value based on the given folding function and
   * seed value.
   *
   * @param bf the folding function
   * @param seed the seed value
   * @param <R> the return type
   * @return the summary value
   */
  <R> R fold(BiFunction<Question, R, R> bf, R seed);

  /**
   * Convert the questionnaire into a single string in the format of
   * Question: [prompt] then two newlines
   * Answer: [answer] two newlines, and so on. Example result for a questionnaire with 3 questions:
   * Question: What is your name?
   *
   * <p>Answer: Sir Lancelot
   *
   * <p>Question: What is your quest?
   *
   * <p>Answer: I seek the Holy Grail.
   *
   * <p>Question: What is your favorite color?
   *
   * <p>Answer: Blue.
   *
   * @return the questionnaire as a String
   */
  String toString();
}

