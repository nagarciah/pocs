package nagarciah.pocs.spark.intro.text;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.tartarus.snowball.ext.SpanishStemmer; 
 
/**  
 * Algortimo para stemming en Espanol (lematizacion)
<<<<<<< HEAD
 * https://www.adictosaltrabajo.com/tutoriales/lucene-ana-lyzers-stemming-more-like-this/#05
=======
>>>>>>> refs/remotes/origin/master
 */ 
public final class MySpanishStemFilter extends TokenFilter { 
     
    private SpanishStemmer stemmer; 
    private CharTermAttribute charTermAttribute;
     
    public MySpanishStemFilter(TokenStream in) { 
    	super(in); 
        stemmer = new SpanishStemmer(); 
        charTermAttribute = input.addAttribute(CharTermAttribute.class);
    } 
     
    /** 
     * Establece un stemmer alternativo
     */ 
    public void setStemmer(SpanishStemmer stemmer) { 
        if ( stemmer != null ) { 
            this.stemmer = stemmer; 
        } 
    }

	@Override
	public boolean incrementToken() throws IOException {
        if(!input.incrementToken()) { 
            return false; 
        } 
        else { 
        	String term = charTermAttribute.toString();
            stemmer.setCurrent(term); 
            stemmer.stem();
            String s = stemmer.getCurrent(); 
            if ( !s.equals( term ) ) { 
            	charTermAttribute.setEmpty().append(s); 
            } 
            return true;
        } 
	} 
}