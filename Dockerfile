FROM openjdk:17-jdk

ENV GLASSFISH_HOME /usr/local/glassfish4
ENV PATH $PATH:$GLASSFISH_HOME/bin

RUN apt-get update && \
    apt-get install -y curl unzip zip inotify-tools && \
    rm -rf /var/lib/apt/lists/*

RUN curl -L -o /tmp/glassfish-4.1.zip http://download.java.net/glassfish/4.1/release/glassfish-4.1.zip && \
    unzip /tmp/glassfish-4.1.zip -d /usr/local && \
    rm -f /tmp/glassfish-4.1.zip

EXPOSE 8080 4848 8181

WORKDIR /usr/local/glassfish4

# Copier le fichier WAR dans le répertoire d'autodéploiement de GlassFish
COPY dist/ProjetFinal.war autodeploy/

# verbose provoque le maintien du processus en avant-plan pour que Docker puisse le suivre
CMD asadmin start-domain --verbose