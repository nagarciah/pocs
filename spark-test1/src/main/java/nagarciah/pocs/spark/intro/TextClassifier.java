package nagarciah.pocs.spark.intro;

import java.io.File;
import java.io.FileReader;
<<<<<<< HEAD
import java.io.IOException;
=======
>>>>>>> refs/remotes/origin/master
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WordlistLoader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;

import com.google.common.collect.Lists;

<<<<<<< HEAD
import nagarciah.pocs.spark.intro.text.MySpanishCleaner;
import nagarciah.pocs.spark.intro.text.MySpanishCleaner2;
=======
>>>>>>> refs/remotes/origin/master
import nagarciah.pocs.spark.intro.text.MySpanishStemFilter;
import nagarciah.pocs.spark.intro.text.MySpellCheckFilter;
import scala.Tuple2;

/**
 * http://help.mortardata.com/technologies/spark/load_and_transform_data
 */
public class TextClassifier {

    private static final String INPUT_DATA_PATH = "src/main/resources/sms-data.txt";
    
	public static void main(String args[]) throws IOException{
		/*
		Corregir ortografía, reemplazando por palabras similares que sobrevivan el steming
		“tokenizes” the text (breaking long strings into single terms), 
		lowercases all the tokens, 
		removes punctuation and stopwords (common words with little predictive value), 
		and stems the words (e.g., “catching” and “catches” both become “catch”)
		*/

		// Inicialización del contexto Spark usando una sola instancia local
        SparkConf config = new SparkConf().setAppName("TextClassifier").setMaster("local[1]");
        JavaSparkContext sc = new JavaSparkContext(config);

		// Cargar y transformar datos de entrenamiento y prueba
		JavaRDD<String> rawData = sc.textFile(INPUT_DATA_PATH);

		// Una función que interprete los datos de entrada
		// Esos datos se esperan en un archivo de texto en el cual cada línea tiene 
		// el formato "label text" separados por tabulación
		JavaPairRDD<String, String> categorizedData = rawData.mapToPair(line -> {
			String[] fields = line.split("\\t");
			return new Tuple2<String, String>(fields[0], fields[1]);
		});
		
		JavaPairRDD<String, List<String>> cleanedData = categorizedData.mapToPair(tuple -> new Tuple2<String, List<String>> (tuple._1, prepareText(tuple._2) ));
		
		//System.out.println( cleanedData.first() );
		cleanedData.foreach(l -> System.out.println(l));
		
		// Hashing term frequency vectorizer with 50k features
		HashingTF htf = new HashingTF(50000);
		
		// Create an RDD of LabeledPoints using category labels as labels and tokenized, hashed text as feature vectors
		// Falta crear un mapa de Double -> label
		JavaRDD<LabeledPoint> hashedData = cleanedData.map(tuple -> new LabeledPoint(getNumericLabel(tuple._1), htf.transform(tuple._2)));
		
		System.out.println( hashedData.first() );
		
		// Ask Spark to persist the RDD so it won't have to be re-created later
		//hashedData.persist(new StorageLevel());
		
		
		
		// Split the data into train and test (70/30)
		JavaRDD<LabeledPoint>[] splits = hashedData.randomSplit(new double[] { 0.7, 0.3 }, System.currentTimeMillis());
		JavaRDD<LabeledPoint> train = splits[0];
		JavaRDD<LabeledPoint> test = splits[1];
		
		// create the trainer and set its parameters
		NaiveBayes nb = new NaiveBayes();
		// train the model
		NaiveBayesModel model = NaiveBayes.train(train.rdd());
		
		
		// Para verificar la exactitud del modelo, se hacen predicciones para el 30% de datos restantes (test) y se compara
		// la predicción con la etiqueta original de ese dato. Luego se evalua cuantas predicciones fueron correctas, del total 
		// evaluado (datos test).

		// Compare predicted labels to actual labels
		JavaRDD<Tuple2<Double, Double>> predictionAndLabels = hashedData.map(point -> new Tuple2<>(model.predict(point.features()), point.label()));
		JavaRDD<Tuple2<Double, Double>> correctPredictions = predictionAndLabels.filter(t -> t._1.compareTo(t._2)==0);
		double accuracy =  ((double) correctPredictions.count()) / ((double)predictionAndLabels.count());
		System.out.println("Precisión de la predicción: " + accuracy);
		
		String text = "Adios buenas noches hasta manana";
		System.out.println("Ejemplo: " + text + "=" + classifyText(model, text));
		
		predictionAndLabels.filter(t -> t._1.compareTo(t._2)==0).foreach(t -> System.out.println(getLabelFromNumber(t._1)));
		

	}
	
	/**
	 * Function to break text into "tokens", lowercase them, remove punctuation and stopwords, and stem them
	 * @throws IOException 
	 */
<<<<<<< HEAD
	private static List<String> prepareText(String dirtyText) throws IOException{
=======
	private static List<String> prepareText(String dirtyText){
>>>>>>> refs/remotes/origin/master
		/*tokenize*/
		//List<String> tokens = (Arrays.asList( dirtyText.split("\\s") ));
		
		// lowercase
		//tokens.stream().forEach(t -> t.toLowerCase());

		//reemplazar abreviaturas/anglisismos/slang comunes: bb xq pq bye hi t d q jajaja
		
		//spell check 
		
		// clean punctuation
		//tokens.stream().forEach(t -> t.replaceAll("á", "a").replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ú", "u"));
		
		// removeStopWords
		//tokens.stream().filter(t -> t.length()>2);
		
		//stem
		//...
		//tokens.stream().forEach(System.out::println);
		
		// Eliminar raices duplicadas?
/*
		tokenize();
		lowercase(tokens);
		correctSpelling(tokens);
		removePunctuation(tokens);
		removeStopWords(tokens);
		stem(tokens);
		*/
		List<String> tokens = cleaner.clean(dirtyText);

		return tokens;
	}

	private static final MySpanishCleaner cleaner = new MySpanishCleaner();
	private static final String DATOS_PERSONALES="Datos personales (Edad, residencia, nombre, cumpleanios, genero)";
	private static final String CONVERSACION="Conversacion (Saludo, Cine, mensajes de buena noche, como estas, que haces)"; 
	private static final String DATOS_FACEBOOK="Datos de facebook";
	private static final String DESCRIPCION_FISICA="Descripcion fisica";
	private static final String SITUACION_SENTIMENTAL="Situacion sentimental";
	private static final String SOLICITUD_AMISTAD="Solicitud de amistad";
	private static final String SOLICITUD_ENCUENTRO="Solicitud de encuentro"; 
	private static final String SOLICITUD_LLAMADA="Solicitud de llamada";
	private static final String SOLICITUD_RECARGA="Solicitud de recarga";
	private static final String IRRECONOCIBLE="(No reconocido)";
	
	private static double getNumericLabel(String label){
		if(DATOS_PERSONALES.equals(label)){
			return 1;
		}else if(CONVERSACION.equals(label)){
			return 2;
		}else if(DATOS_FACEBOOK.equals(label)){
			return 3;
		}else if(DESCRIPCION_FISICA.equals(label)){
			return 4;
		}else if(SITUACION_SENTIMENTAL.equals(label)){
			return 5;
		}else if(SOLICITUD_AMISTAD.equals(label)){
			return 6;
		}else if(SOLICITUD_ENCUENTRO.equals(label)){
			return 7;
		}else if(SOLICITUD_LLAMADA.equals(label)){
			return 8;
		}else if(SOLICITUD_RECARGA.equals(label)){
			return 9;
		}else {
			return 0;
		}
	}
	
	private static String getLabelFromNumber(double number){
		if(Double.compare(number, 1.0)==0){
			return DATOS_PERSONALES;
		}else if(Double.compare(number, 2.0)==0){
			return CONVERSACION;
		}else if(Double.compare(number, 3.0)==0){
			return DATOS_FACEBOOK;
		}else if(Double.compare(number, 4.0)==0){
			return DESCRIPCION_FISICA;
		}else if(Double.compare(number, 5.0)==0){
			return SITUACION_SENTIMENTAL;
		}else if(Double.compare(number, 6.0)==0){
			return SOLICITUD_AMISTAD;
		}else if(Double.compare(number, 7.0)==0){
			return SOLICITUD_ENCUENTRO;
		}else if(Double.compare(number, 8.0)==0){
			return SOLICITUD_LLAMADA;
		}else if(Double.compare(number, 9.0)==0){
			return SOLICITUD_RECARGA;
		}else {
			return IRRECONOCIBLE;
		}
	}
	

	
	private static String classifyText(NaiveBayesModel model, String text) throws IOException{
		List<String> cleanedData = prepareText(text);

		// Hashing term frequency vectorizer with 50k features
		HashingTF htf = new HashingTF(50000);
		
		// Create an RDD of LabeledPoints using category labels as labels and tokenized, hashed text as feature vectors
		// Falta crear un mapa de Double -> label
		Vector hashedData = htf.transform(cleanedData);
	
		return getLabelFromNumber(model.predict(hashedData));
	}
	
}
