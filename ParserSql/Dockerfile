FROM python:3.9
COPY ./ParserSql/ ./ParserSql/
RUN mkdir -p ./ParserSql/ProcurementsDB/
RUN apt update && apt install sqlite3
RUN apt install -y tzdata
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN python -m pip install -r ./ParserSql/requirements.txt
WORKDIR ./ParserSql/
CMD [ "python", "main.py" ]
