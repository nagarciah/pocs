Los Jar auxiliares de Jasper deben referenciarse desde el repositorio de Jasper y no desde Maven Central porque no est[an:
https://jaspersoft.artifactoryonline.com/jaspersoft/webapp/

### Para ejecutar:
1. Instalar erlang (ej: sudo apt install erlang)
2. Instalar e iniciar rabbitmq (ej: rabbitmq-server -detached). Mas: http://www.rabbitmq.com/install-windows-manual.html o https://spring.io/guides/gs/messaging-rabbitmq/ para usar docker compose

https://github.com/spring-projects/spring-batch/blob/master/spring-batch-samples/src/main/resources/META-INF/spring/jobs/messaging/rabbitmq-beans.xml