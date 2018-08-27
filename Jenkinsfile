pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh '''pwd
ls -la
export M2_HOME=/usr/share/maven
export PATH=$PATH:$M2_HOME/bin
mvn --version
mvn -version
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