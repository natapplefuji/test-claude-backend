@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM Apache Maven Wrapper startup script, version 3.3.2

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __ MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_LAUNCHER_ARGS__=-Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%"

@SET MAVEN_PROJECTBASEDIR=%MAVEN_BASEDIR%
@IF "%MAVEN_PROJECTBASEDIR%"=="" SET MAVEN_PROJECTBASEDIR=%~dp0

@SET MAVEN_WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
@SET MAVEN_WRAPPER_PROPERTIES=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.properties

@IF NOT EXIST "%MAVEN_WRAPPER_JAR%" (
  @FOR /F "usebackq tokens=2 delims==" %%i IN (`findstr /i "wrapperUrl" "%MAVEN_WRAPPER_PROPERTIES%"`) DO (
    @SET WRAPPER_URL=%%i
  )
  @IF NOT "%WRAPPER_URL%"=="" (
    @ECHO Downloading Maven Wrapper JAR from %WRAPPER_URL%
    @powershell -Command "Invoke-WebRequest -Uri '%WRAPPER_URL%' -OutFile '%MAVEN_WRAPPER_JAR%'"
  ) ELSE (
    @ECHO ERROR: Cannot find wrapperUrl in maven-wrapper.properties
    @EXIT /B 1
  )
)

@IF "%JAVA_HOME%"=="" (
  @SET JAVA_CMD=java
) ELSE (
  @SET JAVA_CMD=%JAVA_HOME%\bin\java
)

@"%JAVA_CMD%" %MAVEN_OPTS% %MAVEN_DEBUG_OPTS% -classpath "%MAVEN_WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %MAVEN_CONFIG% %*
@IF ERRORLEVEL 1 GOTO error
@GOTO end
:error
@SET ERROR_CODE=%ERRORLEVEL%
:end
@ENDLOCAL & EXIT /B %ERROR_CODE%
