pipeline {
   agent any

   stages {
     stage('Check Commit') {
       script {
         throw new Exception("commit not standard")
       }
     }
   }
}
