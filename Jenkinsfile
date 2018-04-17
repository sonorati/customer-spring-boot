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
        sh "mvn --version"
      }
    }
    stage("publish") {
      when {
        expression {
          return env.BRANCH_NAME == 'master';
        }
      }
      steps {
        echo env.BRANCH_NAME
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
