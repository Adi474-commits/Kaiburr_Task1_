#!/usr/bin/env bash
# ----------------------------------------------------------------------------
# Maven wrapper
# ----------------------------------------------------------------------------
BASEDIR=$(cd "$(dirname "$0")" && pwd)
BASEDIR=$(dirname "$0")
java -cp "$BASEDIR/.mvn/wrapper/maven-wrapper.jar" org.apache.maven.wrapper.MavenWrapperMain "$@"
