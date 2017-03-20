package nagarciah.pocs.spark.intro.text;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class MySpellChecker implements AutoCloseable {
	
	private static String dictionaryPath = "src/main/resources/es_CO.dic"; //"src/main/resources/dictionary.txt"
	private static File spellCheckerDirectory = new File("spellchecker");
	private SpellChecker spellChecker; 
	
	public MySpellChecker() {
		// TODO Hacer que se cargue sólo una vez el diccionario
		loadDictionary();
	}
	
	private void loadDictionary() {
		try{
			File dir = spellCheckerDirectory;
			Directory directory = FSDirectory.open(dir/*.toPath()*/);
			spellChecker = new SpellChecker(directory);
			Dictionary dictionary = new PlainTextDictionary(new File(dictionaryPath));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, null);
			spellChecker.indexDictionary(dictionary, config, false);
			
			// Los mejores resultados se han obtenido con estos parámteros (ej: ojala==ojalá en lugar de ojalar)
			spellChecker.setAccuracy(0.75f);
			spellChecker.setStringDistance(new NGramDistance());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public String checkWord(String word) {
		try {
			String[] suggestions = spellChecker.suggestSimilar(word, 1);
			if (suggestions.length > 0) {
				return suggestions[0];
			} else {
				return word;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isValidWord(String word) throws IOException{
		return this.spellChecker.exist(word);
	}

	@Override
	public void close() throws IOException {
		if(spellChecker != null){
			spellChecker.close();
		}
	}
}
