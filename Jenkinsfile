pipeline  {
environment {
        version ="${env.BUILD_ID}-${env.GIT_COMMIT}"
}

agent any
  stages {

    stage('reading properties from properties file') {
        steps {
            script {
                def props = readProperties file: '/var/jenkins_home/workspace/creds/creds.properties' 
                
                env.registryCredential = props.registryCredential
                env.registryUrl = props.registryUrl
                env.NETWORK = props.NETWORK
                env.authDockerImage = props.authDockerImage
                env.authServiceContainerName  = props.authServiceContainerName 
                env.eurekaHostZone = props.eurekaHostZone
                env.MYSQL_URL = props.MYSQL_URL
                env.MYSQL_USER = props.MYSQL_USER
                env.MYSQL_PASS = props.MYSQL_PASS
                env.configServerUrl = props.configServerUrl
            }
        }
    }

    stage('build') {
      agent {
            docker {
                reuseNode true
                image 'maven:3.8.6-jdk-11'
            }
      }
      steps {
          sh "mvn clean install -DskipTests"
      }

    }

    stage('build and push image') {
      steps {
          script  {
                      app = docker.build("${authDockerImage}:${version}", ".")
                      docker.withRegistry("${registryUrl}", "${registryCredential}") {
                      pushOut =  app.push()
                      }
              }

      }

    }    

    stage('deploy') {
      steps {
            sh 'docker container rm -f $authServiceContainerName || true'
            sh '''
              docker container run -d  --network $NETWORK --name $authServiceContainerName \
              -e EUREKA_HOST_ZONE=$eurekaHostZone \
              -e MYSQL_URL=${MYSQL_URL}/security_db \
              -e MYSQL_USER=$MYSQL_USER \
              -e MYSQL_PASS=$MYSQL_PASS \
              -e CONFIG_SERVER_URL=$configServerUrl \
               $authDockerImage:${version} 
            '''
        }
    }
  }
  }

