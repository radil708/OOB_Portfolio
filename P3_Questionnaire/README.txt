Features:
	- Interface (Super/Sub type relationship) implementation
	- Abstract (Super/Sub class relationship) implementation
	- JUnit Testing
	- Data structure Utilization (list, hashmap, etc..)
	- Iterator Utilization
	

-------------------------------------------------------------------------------------------------------------------
High Level Requirements:
	- Many online questionnaire tools like SurveyMonkey, Doodle Poll, etc. (even Blackboard) 
		allow creating a questionnaire made of several types of questions: Yes/No, 
		Short-answer, Likert scale, etc. 
	- Create classes that represents different types of questions 
	- Create a class that represents a questionnaire
	- Create tests to confirm the code is working correctly
	- All source code should go into src folder
	- All tests should go into the tests folder
===================================================================================================================
Question Specific Objectives:

Each question, irrespective of type, has the following common aspects:

	It has the text of the question itself, and a method getPrompt that returns it.

	It is either required or optional, and has a method isRequired that returns its status.

	It has a method answer(String) that allows one to enter an answer as a String. What the string 
		may contain depends on the type of question.

	It has a method getAnswer that returns the answer to the question, or empty string if there is no answer.

	It has a method copy that returns a copy of the question including all its data.
====================================================================================================================
The types of questions are:

	YesNo: this can be answered in one of two ways: yes or no. answer(String) 
		would accept "Yes" or "No" as valid answers, but case-insensitive, so 
		"yes" or "NO" would also be valid.

	ShortAnswer: this can be answered in at most 280 characters, including spaces.

	Likert: this can be answered on a fixed, 5-point Likert 
		scale (Strongly Agree, Agree, Neither Agree nor Disagree, Disagree, 
		Strongly Disagree). answer(String) would accept only these precise words 
		as valid answers, but again case-insensitive.

