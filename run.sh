#!/bin/sh
echo "Starting Linux Gnome based terminals"
gnome-terminal -x  ./mvnw -f test-assignment-client/pom.xml spring-boot:run &&
gnome-terminal -x  ./mvnw -f test-assignment-server/pom.xml spring-boot:run
