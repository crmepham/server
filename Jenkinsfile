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
    stage('Push to Docker Cloud') {
      parallel {
        stage('Push to Docker Cloud') {
          steps {
            echo 'Pushing images to Docker Cloud'
          }
        }
        stage('Pushing frontend') {
          steps {
            sh '''docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
docker tag server/frontend-service crmepham/server-frontend-service
docker push crmepham/server-frontend-service'''
          }
        }
        stage('Pushing backend') {
          steps {
            sh '''docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
docker tag server/backend-service crmepham/server-backend-service
docker push crmepham/server-backend-service'''
          }
        }
        stage('Pushing crawler') {
          steps {
            sh '''docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
docker tag server/crawler-service crmepham/server-crawler-service
docker push crmepham/server-crawler-service'''
          }
        }
      }
    }
  }
}