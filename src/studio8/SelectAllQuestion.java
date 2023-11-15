package studio8;

public class SelectAllQuestion extends MultipleChoiceQuestion {

	private int totalPoints;
	
	public SelectAllQuestion(String prompt, String answer, String[] choices) {
		// Calls MultipleChoice constructor, 1 point per choice
		super(prompt, answer, 1, choices);
		
		totalPoints = choices.length;
	}
	
	
	public int checkAnswer(String givenAnswer) {
		// Return partial credit (if earned)!
		int lostPoints = 0;
		
		// Convert given answer to charArray to compare to actual answer
		char[] array = givenAnswer.toCharArray();
		
		// Deduct a point for every wrong answer
		for (int i = 0; i < array.length; i++) {	
			if (!(this.getAnswer().contains(array[i] + ""))) { 
				lostPoints++;
			} 
		}
		
		// Deduct a point for every unmarked correct answer
		char[] correct = this.getAnswer().toCharArray();
		
		for (int i = 0; i < correct.length; i++) { 
			if (!(givenAnswer.contains(correct[i] + ""))) {
				lostPoints++;
			}
		}
		
		return totalPoints - lostPoints;
	}
	
	public static void main(String[] args) {
		String[] choices = {"instance variables", "git", "methods", "eclipse"};
		Question selectAll = new SelectAllQuestion("Select all of the following that can be found within a class:", "13", choices);
		selectAll.displayPrompt();
		System.out.println(selectAll.checkAnswer("hi")); //no credit
		System.out.println(selectAll.checkAnswer("13")); //full credit
		System.out.println(selectAll.checkAnswer("31")); //full credit
		System.out.println(selectAll.checkAnswer("1")); //3 points
		System.out.println(selectAll.checkAnswer("3")); //3 points
		System.out.println(selectAll.checkAnswer("23")); //2 points
		System.out.println(selectAll.checkAnswer("34")); //2 points
		System.out.println(selectAll.checkAnswer("4")); //1 point
		System.out.println(selectAll.checkAnswer("124")); //1 point
		System.out.println(selectAll.checkAnswer("24")); //0 points
		
	}
}
