
pipeline {
  agent {
    label "prod"
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '2'))
    disableConcurrentBuilds()
  }
  stages {
    stage("build") {
      steps {   
        sh "mvn clean install"
        sh "docker image build -t seon/order-tiger-demo ."
      }
    }
    stage("publish") {
      steps {
        withDockerRegistry([ credentialsId: "docker-hub", url: "" ]) {
          sh "docker tag seon/order-tiger-demo seon/order-tiger-demo:1.${env.BUILD_NUMBER}"
          sh "docker image push seon/order-tiger-demo:latest"
          sh "docker image push seon/order-tiger-demo:1.${env.BUILD_NUMBER}"
        }
      }
    }
  }
  post {
    success {
      slackSend(
        color: "good",
        message: "seon/order-tiger-demo:${env.BUILD_NUMBER} was deployed to the cluster. Verify that it works correctly!"
      )
    }
    failure {
      slackSend(
        color: "danger",
        message: "${env.JOB_NAME} failed: ${env.RUN_DISPLAY_URL}"
      )
    }
  }
}
