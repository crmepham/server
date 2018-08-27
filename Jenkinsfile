pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''pwd
ls -la
./mvnw clean verify package -am -Dconfig.build="${BUILD_NUMBER}"
mv ./frontend-service/target/frontend-service-1.0.jar ./frontend-service-${BUILD_NUMBER}-${GIT_COMMIT}.jar
ls -la'''
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