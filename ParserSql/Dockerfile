FROM python:3.9
COPY ./ParserSql/ ./
RUN mkdir ./ProcurementsDB/
RUN apt update && apt install sqlite3
RUN apt install -y tzdata
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN python -m pip install -r ./requirements.txt
CMD [ "python", "main.py" ]
