FROM tomcat:10.1-jdk21-temurin

WORKDIR /app

COPY src/main/java /app/src/main/java
COPY src/main/webapp /app/src/main/webapp

RUN mkdir -p /app/build/classes && \
    javac -cp "/usr/local/tomcat/lib/*:/app/src/main/webapp/WEB-INF/lib/*" \
    -d /app/build/classes \
    $(find /app/src/main/java -name "*.java")

RUN rm -rf /usr/local/tomcat/webapps/ROOT && \
    mkdir -p /usr/local/tomcat/webapps/ROOT && \
    cp -r /app/src/main/webapp/* /usr/local/tomcat/webapps/ROOT/ && \
    mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes && \
    cp -r /app/build/classes/* /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

EXPOSE 8080