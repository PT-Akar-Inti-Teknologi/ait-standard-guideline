pipeline {
   agent any

   stages {
     stage('Check Commit') {
       steps {
        script {
          result = sh (script: "git log -1 | grep -E '(feat|build|chore|fix|docs|refactor|perf|style|test)(\\(.+\\))*:'", returnStatus: true)
          if (result != 0) {
            throw new Exception("failed, not meet commit standard!")
          }
        }
       }
     }

     stage('Build') {
       steps {
         sh 'mvn -Dmaven.test.failure.ignore=true install'
       }
     }

      stage('Sonarqube analysis') {
        environment {
          scannerHome = tool 'sonarqube-scanner'
        }

        steps {
          withSonarQubeEnv(installationName: 'sonarqube') {
            sh '$scannerHome/bin/sonar-scanner'
          }
        }
      }

      stage('Quality Gate') {
        steps {
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }

      stage('Build Image - Push - Deploy') {
         steps {
            script {
               env.PIPELINE_NAMESPACE = "java-standard"
               withCredentials([file(credentialsId: 'ait-k8s_kubeconfig', variable: 'CONFIG'),
                             usernamePassword(credentialsId: 'ait-k8s_docker-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                  sh 'docker login ait-cr.akarinti.tech --username=${USER} --password=${PASS}'
                  sh 'mkdir -p $HOME/.kube'
                  sh 'cat ${CONFIG} > ~/.kube/config'
                  sh 'skaffold run -n ait-standard'
                  sh 'cd k8s/'
                  sh 'kubectl apply -f depl.yaml -n ait-standard'
                  sh 'kubectl apply -f svc.yaml -n ait-standard'
                  sh 'kubectl apply -f ingress-api.yaml -n ait-standard'
                  sh 'kubectl rollout status -f depl.yaml -n ait-standard'
                  sh 'kubectl get all,ing  -n ait-standard'
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
