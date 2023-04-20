pipeline {
  agent any

  environment {
    DOCKERHUB_CREDENTIALS = credentials('docker_hub')
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/sarbanjeet/OnlineFoodOrderApplication.git'
      }
    }
    stage('Build') {
      steps {
        withMaven(
          maven: 'Maven_Home'
        ) {
          // To run Maven on a Windows agent, use
          sh "mvn package -Dmaven.test.skip"
        }
      }
    }

    stage('Test') {
      steps {
        withMaven(
          maven: 'Maven_Home'
        ) {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/*.jar'
        }
      }
    }

    stage('Run Sonar Scanner') {
      steps {
        withMaven(
          maven: 'Maven_Home'
        ) {
          sh "mvn sonar:sonar"
        }
      }
    }

    stage('Docker Build Image') {
      agent any
      steps {
        sh 'docker build -t online-food-order-application:latest .'
      }
    }

    stage('Tag Docker Image') {
      agent any
      steps {
        sh 'docker tag online-food-order-application:latest sarbanjeet/online-food-order-application:latest'
      }
    }

    stage('Login Docker Hub') {
      agent any
      steps {
        sh 'echo | set /p="Sarbu@888" | docker login --username sarbanjeet --password-stdin'
      }
    }
    stage('Push Docker Image') {
      agent any
      steps {
        sh 'docker push sarbanjeet/online-food-order-application:latest'
      }
    }
    stage('Deploy Web') {
      agent any
      steps {
        sh 'ansible-playbook playbook.yml'
      }
    }
  }
}