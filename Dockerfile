FROM tomcat:11-jre25
ADD  ./build/libs/diving_api*.war /usr/local/tomcat/webapps/
