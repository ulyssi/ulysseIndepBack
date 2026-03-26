cd  deploy/diving_api_liv
mv build/libs/diving_api.war build/libs/diving_api.war
docker build -t diving_api .
docker stop diving_api
docker rm diving_api
docker run  --restart=always -p 8585:8080 -d  --name diving_api  --net=my_bridge_diving diving_api
sleep 25
docker logs diving_api
cd ~
rm -rf   /home/pi/deploy/diving_api_liv


