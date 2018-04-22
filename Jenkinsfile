
pipeline {
  agent {
    label "prod"
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    disableConcurrentBuilds()
  }
  stages {
    stage("deploy") {
      steps {
        withDockerRegistry([ credentialsId: "dockerhub", url: "" ]) {
            withEnv(["DOCKER_HOST=tcp://${env.PROD_IP}:2376 DOCKER_TLS_VERIFY=1" ])  {
              sh "docker service update --image seon/order-tiger-api:0.4 order"
            }
        }
      }
    }
  }
}
