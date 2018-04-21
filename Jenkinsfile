
pipeline {
  agent {
    label "prod"
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
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

    stage("deploy") {
      steps {
        withEnv([
                  "DOCKER_TLS_VERIFY=1",
                  "DOCKER_HOST=tcp://${env.CLUSTER_IP}:2376",
                  "DOCKER_CERT_PATH=/machines/${env.CLUSTER_NAME}"
              ])


        sh "docker service update --image seon/order-tiger-demo:1.${env.BUILD_NUMBER} customer"
      }
    }

  }
  post {
    success {
      slackSend(
        color: "good",
        message: "seon/order-tiger-demo:1.${env.BUILD_NUMBER} was deployed to the cluster. Verify that it works correctly!"
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
