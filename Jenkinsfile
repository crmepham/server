pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''ls -la
java -jar frontend-service.jar
'''
      }
    }
    stage('Start applications') {
      steps {
        sh '''ps | grep frontend-service | awk \'{print $1}\' | xargs kill -9 || true
rm frontend-service.jar | true
./mvnw clean verify package -Pdocker -am -Dconfig.build="${BUILD_NUMBER}" -f frontend-service/pom.xml
mv frontend-service/target/frontend-service-1.0-SNAPSHOT.jar frontend-service.jar
chmod +x frontend-service.jar
nohup java -jar -Dspring.profiles.active=docker frontend-service.jar &'''
      }
    }
  }
}