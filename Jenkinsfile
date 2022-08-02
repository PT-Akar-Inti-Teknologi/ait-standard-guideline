pipeline {
   agent any

   stages {
      stage('Check Commit') {
         steps {
            script {
              result = sh (script: "git log -1 | grep -E '(feat|build|chore|fix|docs|refactor|perf|style|test):'", returnStatus: true)
              if (result != 0) {
                throw new Exception("error")
              }
            }
         }
      }

   }

   post {
       failure {
         emailext subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
           body: "${currentBuild.currentResult}: Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
           recipientProviders: [
             [$class: 'DevelopersRecipientProvider'],
             [$class: 'RequesterRecipientProvider']
           ]
       }

       always {
         cleanWs()
       }
     }
}
