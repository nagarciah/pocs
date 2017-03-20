package nagarciah.pocs.spark.intro.text;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
//import org.apache.lucene.analysis.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

public class LuceneTester {

	public static final String CONTENTS = "contents";
	public static final String STOPWORDS_PATH = "src/main/resources/stopwords.es";

	public static void main(String[] args) {
		LuceneTester tester;

		tester = new LuceneTester();

		try {
			tester.displayTokenUsingStandardAnalyzer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Set<?> stopWords = StopFilter.makeStopSet(Version.LUCENE_36, Arrays.asList("y", "o"));

	/**
	 * http://members.unine.ch/jacques.savoy/clef/index.html
	 * http://members.unine.ch/jacques.savoy/clef/spanishSmart.txt
	 */
	private void displayTokenUsingStandardAnalyzer() throws IOException {
		String text = "Lucene is simple yet powerful java based search library.";
		text = "me inspiras confianza y ojali algun rat pueda comtart un problma q tub hace algunos a}s y el xq no quiero tener enamorado o hacerm d comprms";
		
		FileReader stopWordsReader = new FileReader(new File(STOPWORDS_PATH));
		stopWords = WordlistLoader.getWordSet(stopWordsReader, Version.LUCENE_36);
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		
		// Tokenizaci[on
		TokenStream tokenStream = analyzer.tokenStream(CONTENTS, new StringReader(text));
		
		// Minusculas
		tokenStream = new LowerCaseFilter(Version.LUCENE_36, tokenStream);
		
		// Spell check
		tokenStream.reset();
		tokenStream = new MySpellCheckFilter(tokenStream);

		// Stopwords
		tokenStream = new StopFilter(Version.LUCENE_36, tokenStream, stopWords);
		
		tokenStream = new MySpanishStemFilter(tokenStream);
		//tokenStream.reset();

		CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		while (tokenStream.incrementToken()) {
			System.out.print("[" + termAttribute.toString() + "] ");
		}
	}
}