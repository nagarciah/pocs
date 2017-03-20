package nagarciah.pocs.spark.intro.text;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * Filtro para verificar ortografia del flujo de tokens
<<<<<<< HEAD
 * http://stackoverflow.com/questions/2638200/how-to-get-a-token-from-a-lucene-tokenstream
 * @author nelson
 */
public class MySpellCheckFilter extends TokenFilter {

	private MySpellChecker spChecker;
	CharTermAttribute charTermAttribute;
	
	protected MySpellCheckFilter(TokenStream input) {
		super(input);
		/*try {
			input.reset();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		*/spChecker = new MySpellChecker();
=======
 * @author nelson
 */
public class MySpellCheckFilter extends TokenFilter {

	private MySpellChecker spChecker;
	CharTermAttribute charTermAttribute;
	
	protected MySpellCheckFilter(TokenStream input) {
		super(input);
		try {
			input.reset();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		spChecker = new MySpellChecker();
>>>>>>> refs/remotes/origin/master
		charTermAttribute = input.addAttribute(CharTermAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		CharTermAttribute charTermAttribute = input.addAttribute(CharTermAttribute.class);
		
		if(input.incrementToken()){
			String term = charTermAttribute.toString();
			if(!spChecker.isValidWord(term)){
				term = spChecker.checkWord(term);
				charTermAttribute.setEmpty().append(term);
			}
			return true;
		}else{
			return false;
		}
	}

}
