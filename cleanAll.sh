#!/bin/bash

maven_cli_opts="-U -Dmaven.repo.local=$PWD/.m2/repository"

mvn clean $maven_cli_opts

