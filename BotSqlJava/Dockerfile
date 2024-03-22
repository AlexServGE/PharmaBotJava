FROM eclipse-temurin
COPY ./BotSqlJava/target/BotSqlJava-1.0-SNAPSHOT-jar-with-dependencies.jar ./BotSqlJava/BotSqlJava-1.0-SNAPSHOT-jar-with-dependencies.jar
RUN mkdir -p ./BotSqlJava/ProcurementsDB/
RUN apt update
RUN apt install sqlite3 -y
RUN apt install -y tzdata
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR ./BotSqlJava/
CMD ["java", "-jar", "BotSqlJava-1.0-SNAPSHOT-jar-with-dependencies.jar"]