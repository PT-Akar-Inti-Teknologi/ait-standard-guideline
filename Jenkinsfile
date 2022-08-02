pipeline {
   agent any

   stages {

      stage('Check Commit') {
         steps {
            script {
              throw new Exception("commit not standard")
            }
         }
      }

   }
}
