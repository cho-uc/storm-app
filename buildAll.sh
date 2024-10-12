#!/bin/bash

maven_cli_opts="-U -Dmaven.repo.local=$PWD/.m2/repository"

mvn clean install $maven_cli_opts -DskipTests
# mvn test $maven_cli_opts
