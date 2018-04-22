
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
            withEnv([
                  "DOCKER_TLS_VERIFY=1",
                  "DOCKER_HOST=tcp://${env.PROD_IP}:2376",
                  "DOCKER_CERT_PATH=/machines/${env.CLUSTER_NAME}"
                      ])
                sh "docker service update --image seon/order-tiger-api:0.15 order"
        }
      }
    }
  }
}
