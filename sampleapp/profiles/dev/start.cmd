:: Sample app start script

@echo off
::::::::::::::::::::::::::::::::::::
:: start.cmd                      ::
::                                :: 
:: DEV Sample App Startup Script  :: 
::                                :: 
:: Explicit logging.config added  ::
:: for clarity in case a          ::
:: different path is needed       ::
:: in the future. Defaults to     ::
:: current directory if           ::
:: logback.xml present.           ::
::::::::::::::::::::::::::::::::::::

:: Set JAVA_HOME
set JAVA_HOME=C:\PROGRA~1\Java\jdk1.7.0_67\bin

:: Display Variables and Launch
set JAVA_HOME
call %JAVA_HOME%\java -jar -Dspring.profiles.active=dev -Dserver.port=8888^
   -DjdbcProps=file:/C:/Users/hqrsingh/Development/workspaces/eclipse/springboot-workspace/springboot/sampleapp/profiles/dev/jdbc.properties^
   -DmailProps=file:/C:/Users/hqrsingh/Development/workspaces/eclipse/springboot-workspace/springboot/sampleapp/profiles/dev/mail.properties^
   -DewmsProps=file:/C:/Users/hqrsingh/Development/workspaces/eclipse/springboot-workspace/springboot/sampleapp/profiles/dev/ewms.properties^
   -Dlog.file=sampleapp^
   -Dlog.base.dir=C:/Users/hqrsingh/Development/workspaces/eclipse/springboot-workspace/springboot/sampleapp^
   C:\Users\hqrsingh\Development\workspaces\eclipse\springboot-workspace\springboot\sampleapp\target\sampleapp-0.0.1-SNAPSHOT.jar --logging.config=file:/C:/Users/hqrsingh/Development/workspaces/eclipse/springboot-workspace/springboot/sampleapp/src/main/resources/logback.xml
pause
