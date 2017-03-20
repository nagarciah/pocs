package nagarciah.pocs.spark.intro.text;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class MySpellCheckerTest {

	@Test
	public void checkSentence() throws Exception {

		// TODO Falta el assert
		try(MySpellChecker spellChecker = new MySpellChecker()){
			String sentence = "me inspiras confianza y ojala algun rat pueda comtart un problma q tub hace algunos a}s y el xq no quiero tener enamorado o hacerm d comprms";
	
			String[] words = sentence.split("\\s"); // tokenizacion burda
			String[] suggestions = new String[words.length];
	
			for(int i=0; i<words.length; i++){
				if(spellChecker.isValidWord(words[i])){
					suggestions[i] = words[i] + " (correcta)";
				}else{
					suggestions[i] = spellChecker.checkWord(words[i]);
				}
				
				System.out.println(suggestions[i]);;
			}
		}
	}

}
