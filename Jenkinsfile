import java.text.SimpleDateFormat

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
        script {
          def dateFormat = new SimpleDateFormat("yy.MM.dd")
          currentBuild.displayName = dateFormat.format(new Date()) + "-" + env.BUILD_NUMBER
        }
        sh "mvn clean install"
        sh "docker image build -t seon/order-tiger-demo ."
      }
    }
    stage("publish") {
      steps {
        withDockerRegistry([ credentialsId: "docker-hub", url: "" ]) {
          sh "docker tag seon/order-tiger-demo seon/order-tiger-demo:${currentBuild.displayName}"
          sh "docker image push seon/order-tiger-demo:latest"
          sh "docker image push seon/order-tiger-demo:${currentBuild.displayName}"
        }
      }
    }
  }
  post {
    success {
      slackSend(
        color: "good",
        message: "seon/order-tiger-demo:${currentBuild.displayName} was deployed to the cluster. Verify that it works correctly!"
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
