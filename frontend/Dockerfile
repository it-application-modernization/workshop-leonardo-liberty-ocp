FROM openliberty/open-liberty:kernel-slim-java11-openj9-ubi

COPY --chown=1001:0 target/frontend-1.0-SNAPSHOT.war /config/apps
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
RUN features.sh
RUN configure.sh
