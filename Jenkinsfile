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
        sh '''cd /backend-service/target
ps | grep backend-service | awk \'{print $1}\' | xargs kill -9 || true
nohup java -jar backend-service-1.0-SNAPSHOT.jar &'''
      }
    }
  }
}