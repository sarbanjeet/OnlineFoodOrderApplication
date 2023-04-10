pipeline {
  agent any

  stages {
   stage('Git pull') {
    steps {
           // Get some code from a GitHub repository
           git branch: 'main', url: 'https://github.com/sarbanjeet/OnlineFoodOrderApplication.git'
           }
   }
    stage('Maven install') {

      steps {
        // Run Maven on a Unix agent.
        // sh "mvn -Dmaven.test.failure.ignore=true clean package"
        withMaven(
          maven: 'Maven_Home'
        ) {
          // To run Maven on a Windows agent, use
          bat "mvn -Dmaven.test.failure.ignore=true clean package"
        }
      }

      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts 'target/*.jar'
        }
      }
    }

    stage('Docker Build') {
      steps {
        bat 'docker build -t online-food-order-application:latest .'
      }
    }
  }
}