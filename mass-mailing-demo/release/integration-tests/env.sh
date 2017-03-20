APP_HOME=$(pwd)/../
HTTP_SERVER_PORT=8080
JAR_FILE=$APP_HOME/target/mass-mailing-demo-0.0.2-SNAPSHOT.jar
JAVA_PROXY_ARGS="-Dhttps.proxyHost=192.168.0.6 -Dhttps.proxyPort=3128"
APP_PROPERTIES="-Dspring.mail.host=localhost -Dspring.mail.port=2525"
#APP_PROPERTIES="-Dspring.config.location=file:$(pwd)/application-integration-tests.properties"
