#!/usr/bin/env bash
git ls-files -o
echo "$DOCKER_PASSWORD" | docker login docker.pkg.github.com -u "$DOCKER_USERNAME" --password-stdin
docker build . --tag "docker.pkg.github.com/$TRAVIS_REPO_SLUG/app:1.0" --build-arg JAR_FILE=./build/libs/dominigeiger-0.0.1-SNAPSHOT.jar
docker push "docker.pkg.github.com/$TRAVIS_REPO_SLUG/app:1.0"