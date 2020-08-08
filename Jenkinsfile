pipeline {
  agent { label 'master' }
  environment {
    JENKINS_NODE_COOKIE = "donotkillme"
  }
  stages {
    stage('test') {
      steps {
        echo 'test'
        sh '#!/bin/bash -ilex'
        sh 'gradle test'
      }
    }
    stage('build') {
      steps {
        echo 'build'
        sh '#!/bin/bash -ilex'
        sh 'gradle build'
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