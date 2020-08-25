
1. docker-compose 실행 

cd D:\Project\workspace.selenium\chicor_qa\docker

docker-compose up

docker ps

http://localhost:4444/grid/console

2. 빌드 

docker build -t jenkins-seleniumgrid .    

3. 실행 

docker run -d -p 9090:8080 -p 50001:50000 -v D:/app/jenkins:/var/jenkins_home --restart unless-stopped --name jenkins-seleniumgrid jenkins-seleniumgrid


http://localhost:9090


4. vnc 비밀번호 

secret




\

