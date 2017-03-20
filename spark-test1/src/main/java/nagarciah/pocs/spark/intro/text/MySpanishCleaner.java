package nagarciah.pocs.spark.intro.text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class MySpanishCleaner {

	public static final String CONTENTS = "contents";
	public static final String STOPWORDS_PATH = "src/main/resources/stopwords.es";

	public static void main(String[] args) {
		MySpanishCleaner tester;

		tester = new MySpanishCleaner();

		try {
			//String text = "me inspiras confianza y ojali algun rat pueda comtart un problma q tub hace algunos a}s y el xq no quiero tener enamorado o hacerm d comprms";
			//String text = "solo djam curart las hridas d tu corazon y mnt y t promto q sra muy fliz y mjor q ants y nunk rcordara l pasado ni l dolor kusado solo decidase vida";
			String text = "yo practiqué guitarra un rato después del partido y luego me metí entre las cobijas a leer porque estaba haciendo mucho frío";
			for(String s : tester.clean(text)){
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Set<?> stopWords;
	private FileReader stopWordsReader;
	private Analyzer analyzer;

	public MySpanishCleaner(){
		try {
			stopWordsReader = new FileReader(new File(STOPWORDS_PATH));
			stopWords = WordlistLoader.getWordSet(stopWordsReader, Version.LUCENE_36);		
			analyzer = new StandardAnalyzer(Version.LUCENE_36);	
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * http://members.unine.ch/jacques.savoy/clef/index.html
	 * http://members.unine.ch/jacques.savoy/clef/spanishSmart.txt
	 */
	public List<String> clean(String text) throws IOException {
		try(TokenStream tokenStream = analyzer.tokenStream(CONTENTS, new StringReader(text))){
			// Tokenizaci[on
			//tokenStream = analyzer.tokenStream(CONTENTS, new StringReader(text));
			
			// Minusculas
			TokenStream tokenStream2 = new LowerCaseFilter(Version.LUCENE_36, tokenStream);
			
			// Spell check
			tokenStream.reset();
			TokenStream tokenStream3 = new MySpellCheckFilter(tokenStream2);
	
			// Stopwords
			TokenStream tokenStream4 = new StopFilter(Version.LUCENE_36, tokenStream3, stopWords);
			
			TokenStream tokenStream5 = new MySpanishStemFilter(tokenStream4);
			
			
			tokenStream5.reset();
			CharTermAttribute termAttribute = tokenStream5.addAttribute(CharTermAttribute.class);
			List<String> cleanWords = new ArrayList<>(); 
			while (tokenStream5.incrementToken()) {
				//System.out.print("[" + termAttribute.toString() + "] ");
				cleanWords.add(termAttribute.toString());
			}
			
			return cleanWords;
		}
	}
}