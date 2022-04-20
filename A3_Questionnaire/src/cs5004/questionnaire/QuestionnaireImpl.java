package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * This defines all the methods contained in the questionnaire interface.
 */
public class QuestionnaireImpl implements Questionnaire {
  protected List<Question> listOfQuestions;
  protected HashMap<String, Question> dictionary;


  /**
   * This constructor creates a list of questions. By default this is empty.
   */
  public QuestionnaireImpl() {
    this.listOfQuestions = new ArrayList<Question>();
    //Every list will need iterator to check for duplicate ID's or questions in the next methods.
    this.dictionary = new HashMap<>(); //This is the dictionary for string ID's and question
  }

  /**
   * This will check to see if an identifier already exists in the hashmap of key: identifier,
   * value: question object.
   *
   * @param id is the identifier as a string that should correspond to an key in the hashmap.
   * @return a boolean depending if the identifier already exists. It returns true if it already
   *     exists and false if not.
   */
  private boolean idPresentChecker(String id) {
    boolean idPresent = false;
    Iterator myIterator = this.dictionary.entrySet().iterator();
    while (myIterator.hasNext()) {
      Map.Entry mapElement = (Map.Entry) myIterator.next();
      //Here we check if an identifier has been used before
      if (mapElement.getKey() == id) {
        idPresent = true;
      }
    }
    return idPresent;
  }


  @Override
  public void addQuestion(String identifier, Question q) {
    if (identifier == null || identifier.equals("") ) {
      throw new IllegalArgumentException("Cannot "
            + "be null"); }
    if (q == null) {
      throw new IllegalArgumentException("Cannot be null"); }
    //The method checks if the "ID" is already taken.
    boolean idPresent = idPresentChecker(identifier);
    if (idPresent) {
      throw new IllegalArgumentException("There is already an identifier that exists with the "
              + " same name, please use a different identifier");
    }
    this.listOfQuestions.add(q); // This adds the question to the array list
    this.dictionary.put(identifier, q); // This links the string ID to the question


  }

  @Override
  public void removeQuestion(String identifier) {
    if (identifier == null || identifier.equals("") ) {
      throw new IllegalArgumentException(); }
    boolean idPresent = idPresentChecker(identifier);
    if (!idPresent) {
      throw new NoSuchElementException("No such identifier exists");
    }
    Question object;
    object = this.dictionary.get(identifier); // This gets the question from the hashmap.
    this.listOfQuestions.remove(object); // This removes the question from the list of questions.
    this.dictionary.remove(identifier); // This removes the question from the dictionary/hashmap.

  }

  @Override
  public Question getQuestion(int num) {
    //Returns the question element at the index "num".
    if (num > listOfQuestions.size() || num < 0) {
      throw new IndexOutOfBoundsException("The index input is"
            + "larger than the final index of the list"); }
    return this.listOfQuestions.get(num); //
  }

  @Override
  public Question getQuestion(String identifier) {
    if (identifier == null || identifier.equals("") ) {
      throw new IllegalArgumentException(); }
    Question questionObject = null;
    boolean idPresent = idPresentChecker(identifier);
    if (!idPresent) {
      throw new NoSuchElementException("No such identifier exists");
    }
    Iterator getIterator = this.dictionary.entrySet().iterator();
    while (getIterator.hasNext()) {
      Map.Entry mapElement = (Map.Entry) getIterator.next();
      if (mapElement.getKey() == identifier) {
        questionObject = (Question) mapElement.getValue();
      }
    }
    return questionObject;
  }



  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions = new ArrayList<Question>();
    for (int i = 0; i < this.listOfQuestions.size(); i++) {
      Question quesObj = this.listOfQuestions.get(i);
      if (quesObj.isRequired()) {
        requiredQuestions.add(quesObj);
      }
    }
    return requiredQuestions;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions = new ArrayList<Question>();
    for (int i = 0; i < this.listOfQuestions.size(); i++) {
      Question quesObj = this.listOfQuestions.get(i);
      if (!quesObj.isRequired()) {
        optionalQuestions.add(quesObj);
      }
    }
    return optionalQuestions;
  }

  @Override
  public boolean isComplete() {
    int totalRequired = 0;
    int requiredAnswered = 0;
    for (int i = 0; i < this.listOfQuestions.size(); i++) {
      Question quesObj = this.listOfQuestions.get(i);
      if (quesObj.isRequired()) {
        totalRequired += 1;
      }
      String answer = quesObj.getAnswer();
      if (!answer.equals("")) {
        requiredAnswered += 1;
      }
    }
    return totalRequired == requiredAnswered;
  }

  @Override
  public List<String> getResponses() {
    List<String> responseList = new ArrayList<>();
    String str = "";
    for (int i = 0; i < this.listOfQuestions.size(); i++) {
      Question quesObj = this.listOfQuestions.get(i);
      str = quesObj.getAnswer();
      responseList.add(str);
    }
    return responseList;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    if (pq == null) {
      throw new IllegalArgumentException("The predicate cannot be null");
    }
    if (this.listOfQuestions.equals(new ArrayList<Question>())) {
      return new QuestionnaireImpl(); }
    Questionnaire newListQuestions = new QuestionnaireImpl();
    for (Map.Entry entry : this.dictionary.entrySet()) {
      if (pq.test(this.dictionary.get(entry.getKey()))) {
        newListQuestions.addQuestion((String) entry.getKey(), this.dictionary.get(entry.getKey()));
      }
    }
    return newListQuestions;
  }



  @Override
  public void sort(Comparator<Question> comp) {
    if (comp == null) {
      throw new IllegalArgumentException("The comparator cannot be null");
    }
    Collections.sort(this.listOfQuestions, comp);
  }


  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    // The key to this signature is that the return of the bifunction should match the
    // return of this method.
    if (bf == null) {
      throw new IllegalArgumentException("The bifunction cannot be null");
    }
    R accumulator = seed;
    for (Question eachQuestion : this.listOfQuestions) {
      accumulator = bf.apply(eachQuestion, accumulator);
    }
    return accumulator;

  }

  @Override
  public String toString() {
    BiFunction<Question, String, String> bf = (x, y) -> "Question: " + x.getPrompt()
            + "\n\nAnswer: " + x.getAnswer() + "\n\n" + y;
    String str = this.fold(bf, "");
    str = str.trim();
    return str;
  }
}
