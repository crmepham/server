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
    stage('Deploy To Docker') {
      steps {
        echo 'Deploying...'
      }
    }
  }
}