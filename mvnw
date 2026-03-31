#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup script, version 3.3.2
# ----------------------------------------------------------------------------

# If JAVA_HOME is set, use it; otherwise use java from PATH
if [ -n "$JAVA_HOME" ] ; then
  if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
    JAVACMD="$JAVA_HOME/jre/sh/java"
  else
    JAVACMD="$JAVA_HOME/bin/java"
  fi
  if [ ! -x "$JAVACMD" ] ; then
    die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
  fi
else
  JAVACMD="java"
  if ! command -v java > /dev/null 2>&1
  then
    die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
  fi
fi

# Locate the script directory
MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-}"
if [ -z "$MAVEN_PROJECTBASEDIR" ]; then
  MAVEN_PROJECTBASEDIR=$(pwd)
fi

MAVEN_WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

# Download wrapper jar if missing
if [ ! -f "$MAVEN_WRAPPER_JAR" ]; then
  WRAPPER_URL=$(grep 'wrapperUrl' "$MAVEN_WRAPPER_PROPERTIES" | cut -d'=' -f2)
  if command -v curl > /dev/null 2>&1; then
    curl -o "$MAVEN_WRAPPER_JAR" "$WRAPPER_URL" -fsSL
  elif command -v wget > /dev/null 2>&1; then
    wget -q -O "$MAVEN_WRAPPER_JAR" "$WRAPPER_URL"
  else
    echo "ERROR: Cannot download maven-wrapper.jar — install curl or wget" >&2
    exit 1
  fi
fi

exec "$JAVACMD" \
  -classpath "$MAVEN_WRAPPER_JAR" \
  "-Dmaven.multiModuleProjectDirectory=$MAVEN_PROJECTBASEDIR" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
