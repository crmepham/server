pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''pwd
ls -ltr

./mvnw.sh clean verify package -am -Dconfig.build="${BUILD_NUMBER}"
mv ./crawler-service/target/crawler-service-1.0.jar ./crawler-service-${BUILD_NUMBER}-${GIT_COMMIT}.jar
ls -ltr'''
      }
    }
    stage('Test') {
      steps {
        echo 'Testing...'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying...'
      }
    }
  }
}