package nagarciah.pocs.spark.intro;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

/**
 * http://help.mortardata.com/technologies/spark/load_and_transform_data
 */
public class WordsCounter {

    private static final String INPUT_DATA_PATH = "src/main/resources/sms-data.txt";
    
	public static void main(String args[]){
		// Inicialización del contexto Spark usando una sola instancia local
        SparkConf config = new SparkConf().setAppName("TextClassifier").setMaster("local[1]");
        JavaSparkContext sc = new JavaSparkContext(config);

		// Cargar y transformar datos de entrenamiento y prueba
		JavaRDD<String> rawData = sc.textFile(INPUT_DATA_PATH);
		
		rawData
			.map(line -> line.split("\\t")[1])		
			.flatMap(line -> Arrays.asList(line.split("\\s+")))
			.mapToPair(token -> new Tuple2<>(token, 1))
			.reduceByKey((a,b) -> a + b)
			.filter(t -> t._2>10)
			.foreach(t -> System.out.println(t))
		;
	}
}
