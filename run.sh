
cd banco
docker build -t matheusluna/banco .

cd ..
mvn clean package
docker build -t matheusluna/app .

docker run -p 5432:5432 --name banco -d matheusluna/banco
docker run -p 8080:8080 --link banco:banco --name app -d matheusluna/app

