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
         post {
             failure {
                 script {
                     echo "failed, not meet commit standard"
                 }
             }
         }
      }

   }
}
