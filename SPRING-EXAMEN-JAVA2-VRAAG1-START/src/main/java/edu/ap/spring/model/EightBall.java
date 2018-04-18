package edu.ap.spring.model;
import edu.ap.spring.jpa.Question;
import edu.ap.spring.jpa.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EightBall {

	@Autowired
	QuestionRepository repository;
	
	private String[] answers = {"It is certain", 
								"Yes definitely", 
								"Most likely",
								"Outlook good",
								"Better not tell you now",
								"Cannot predict now",
								"Don't count on it", 
								"Outlook not so good"};
	
	public String getRandomAnswer(String question) {
		String answer = "";

		try {
			Question question1 = repository.findByQuestion(question);
			if(question1 == null) {
				List<String> answers = new ArrayList<>();
				repository.findAll().forEach(p -> answers.add(p.getAnswer()));


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
