pipeline {
  agent { label 'master' }
  environment {
    JENKINS_NODE_COOKIE = "donotkillme"
  }
  stages {
    stage('test') {
      steps {
        echo 'test'
        sh 'gradlew test'
      }
    }
    stage('build') {
      steps {
        echo 'build'
        sh 'gradlew build'
      }
    }
    stage('deploy') {
      steps {
        echo 'deploy'
        sh "cp build/libs/*.jar /"
      }
    }
  }
}