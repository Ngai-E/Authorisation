pipeline  {
  environment {
        //  registry = "index.docker.io"
          registryCredential = 'dockerhub'
         registryUrl = 'https://index.docker.io/v2/'

           dockerImage = ''
           serverCredential = 'ssh_stage'

           version ="${env.BUILD_ID}-${env.GIT_COMMIT}"
  }

  agent any
   stages {

      stage('build') {
       agent {
                      docker {
                          /*
                           * Reuse the workspace on the agent defined at top-level of Pipeline but run inside a container.
                           * In this case we are running a container with maven so we don't have to install specific versions
                           * of maven directly on the agent
                           */
                          reuseNode true
                          image 'maven:3.6.3-jdk-8'
                      }
                  }
      steps {
           sh "mvn clean install -s settings.xml -DskipTests"
          }

       }
         stage('containerise') {
             steps {
           script  {
                        app = docker.build("authentication-service:${version}")
						docker.withRegistry(registryUrl,registryCredential) {
                        pushOut =  app.push()
					}
               }

                 }

              }

      stage('deploy') {
      steps {

                                    sh 'chmod +x deploy.sh'
                                    sshagent(credentials: [serverCredential]) {
                                    sh './deploy.sh prod ${version} ${registry}'
                                     }
       }
      }
      }

}
