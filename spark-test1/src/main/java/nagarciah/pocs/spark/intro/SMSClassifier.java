package nagarciah.pocs.spark.intro;

import org.apache.spark.ml.classification.NaiveBayes;
import org.apache.spark.ml.classification.NaiveBayesModel;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;

public class SMSClassifier {

	public static void main(String[] args) {
		/*
		SparkSession spark = SparkSession.builder().appName("JavaNaiveBayesExample").getOrCreate();

		// $example on$
		// Load training data
		Dataset<Row> dataFrame = spark.read().format("libsvm").load("src/main/resources/sample_libsvm_data.txt");
		// Split the data into train and test
		Dataset<Row>[] splits = dataFrame.randomSplit(new double[] { 0.6, 0.4 }, System.curr);
		Dataset<Row> train = splits[0];
		Dataset<Row> test = splits[1];

		// create the trainer and set its parameters
		NaiveBayes nb = new NaiveBayes();
		// train the model
		NaiveBayesModel model = nb.fit(train);
		// compute precision on the test set
		Dataset<Row> result = model.transform(test);
		Dataset<Row> predictionAndLabels = result.select("prediction", "label");
		MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
				.setMetricName("precision");
		System.out.println("Precision = " + evaluator.evaluate(predictionAndLabels));
		// $example off$

		spark.stop();
		*/
	}

}
