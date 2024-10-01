FROM eclipse-temurin
RUN mkdir ./BackSpringJava/
COPY ./BackSpringJava/target/SQLite.demo-0.0.1-SNAPSHOT.jar ./BackSpringJava/SQLite.demo-0.0.1-SNAPSHOT.jar
RUN mkdir ./ProcurementsDB/
RUN apt update
RUN apt install sqlite3 -y
RUN apt install -y tzdata
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
WORKDIR ./BackSpringJava/
CMD ["java", "-jar", "SQLite.demo-0.0.1-SNAPSHOT.jar"]