package edu.ap.spring.model;
import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class EightBall {

	@Autowired
	QuestionRepository repository;
	
	//private List<String> answers = new ArrayList<>();

	private String[] answers = {"It is certain",
			"Yes definitely",
			"Most likely",
			"Outlook good",
			"Better not tell you now",
			"Cannot predict now",
			"Don't count on it",
			"Outlook not so good"};

/*	public EightBall () {
		addAswers();
	}

	private void addAswers() {
		answers.add("It is certain");
		answers.add("Yes definitely");
		answers.add("Most likely");
		answers.add("Outlook good");
		answers.add("Better not tell you now");
		answers.add("Cannot predict now");
		answers.add("Don't count on it");
		answers.add("Outlook not so good");
	}*/
	
	public String getRandomAnswer(String question) {
		String answer = "";

		try {
			Question question1 = repository.findByQuestion(question);
			if(question1 == null) {
				List<String> pastAnswers = new ArrayList<>();
				repository.findAll().forEach(p -> pastAnswers.add(p.getAnswer()));

				Boolean foundAnswer = false;
				Random rand = new Random();
				int i = 0;
				while(foundAnswer == false) {
					int n = rand.nextInt(answers.length);
					String possibleAnswer = answers[n];
					if(!pastAnswers.contains(possibleAnswer)) {
						foundAnswer = true;
						answer = possibleAnswer;
					}
					else {
						if(i >= answers.length) {
							foundAnswer = true;
							answer = possibleAnswer;
						}
					}

					i++;
				}
			}
			else {
				answer = question1.getAnswer();
			}
		}
		catch (Exception e) {}

		return answer;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
}
