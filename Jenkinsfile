
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
        sshagent (credentials: ['quality-cluster']) {
          sh 'ssh -o StrictHostKeyChecking=no -l docker 34.244.104.34 uname -a'
          sh "docker service ls"
        }
      }
    }
  }
}
