./gradlew  bootWar
mv build/libs/diving_api*.war build/libs/diving_api.war
sudo docker stop ulysseIndepBack
sudo docker image rm ulysseIndepBack
sudo docker build -t ulysseIndepBack .
sudo docker run -p 8080:8080 --rm --name  ulysseIndepBack ulysseIndepBack

