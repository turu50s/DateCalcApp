release: ./mvnw flyway:migrate -Dflyway.user=$DB_USER_NAME -Dflyway.password=$DB_PASSWORD -Dflyway.url=$DB_SETTING_URL -Dflyway.driver=com.mysql.jdbc.Driver
web: java -Dserver.port=$PORT -jar target/*.jar --server.port=$PORT --spring.profiles.active=heroku --spring.datasource.username=$DB_USER_NAME --spring.datasource.password=$DB_PASSWORD --spring.datasource.url=$DB_SETTING_URL
