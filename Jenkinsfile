pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''./mvnw clean verify package -am -Dconfig.build="${BUILD_NUMBER}"
docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
docker tag server/frontend-service crmepham/server-frontend-service
docker push'''
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