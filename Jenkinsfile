pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''./mvnw clean verify package -Pdocker -am -Dconfig.build="${BUILD_NUMBER}"
'''
      }
    }
    stage('Start applications') {
      steps {
        sh '''ps | grep backend-service | awk \'{print $1}\' | xargs kill -9 || true
ps | grep frontend-service | awk \'{print $1}\' | xargs kill -9 || true
ps | grep crawler-service | awk \'{print $1}\' | xargs kill -9 || true
rm backend-service.jar | true
rm frontend-service.jar | true
rm crawler-service.jar | true
mv backend-service/target/backend-service-1.0-SNAPSHOT.jar backend-service.jar
mv frontend-service/target/frontend-service-1.0-SNAPSHOT.jar frontend-service.jar
mv crawler-service/target/crawler-service-1.0-SNAPSHOT.jar crawler-service.jar
nohup java -jar -Dspring.profiles.active=docker backend-service.jar &
nohup java -jar -Dspring.profiles.active=docker frontend-service.jar &
nohup java -jar -Dspring.profiles.active=docker crawler-service.jar &'''
      }
    }
  }
}