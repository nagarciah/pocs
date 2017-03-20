echo
echo "Usando variables de entorno en '$1/env.sh'"
. ./$1/env.sh
echo 

#JAVA_SECURITY_OPTS=
#JAVA_SECURITY_OPTS=$JAVA_SECURITY_OPTS -Djava.security.debug=certpath
#JAVA_SECURITY_OPTS=$JAVA_SECURITY_OPTS -Djavax.net.debug=trustmanager 
#JAVA_SECURITY_OPTS="$JAVA_SECURITY_OPTS -Djavax.net.ssl.trustStore=$APP_HOME/conf/cacerts"
#JAVA_SECURITY_OPTS="$JAVA_SECURITY_OPTS -Djavax.net.ssl.trustStorePassword=changeit"

echo "====== Iniciando aplicación con variables de entorno: ============="
echo "APP_HOME=$APP_HOME"
echo "HTTP_SERVER_PORT=$HTTP_SERVER_PORT"
echo "JAR_FILE=$JAR_FILE"
echo "JAVA_PROXY_ARGS=$JAVA_PROXY_ARGS"
echo "JAVA_SECURITY_OPTS=$JAVA_SECURITY_OPTS"
echo "APP_PROPERTIES=$APP_PROPERTIES"
echo "==================================================================="
echo

java $APP_PROPERTIES -Dserver.port=$HTTP_SERVER_PORT $JAVA_SECURITY_OPTS $JAVA_PROXY_ARGS -Dapp.home=$APP_HOME -jar $JAR_FILE &
