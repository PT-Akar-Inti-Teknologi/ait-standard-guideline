pipeline {
   agent any

   stages {

      stage('Check Commit') {
         steps {
            script {
              throw new Exception("commit not standard")
            }
         }
         post {
             success {
                 script {
                     echo "success"
                 }
             }
             failure {
                 script {
                     echo "failed, not meet commit standard"
                 }
             }
         }
      }

   }
}
