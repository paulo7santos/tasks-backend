pipeline {
    agent any
    stages{
        stage('Backend Build'){
            steps{
                 sh 'echo "Building backend" '
                 sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests'){
            steps{
                 sh 'echo "Starting Unit Tests" '
                 sh 'mvn test'
            }
        }
        stage('Sonar Analysis'){
            environment{
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL'){
                 sh 'echo "Starting Sonar Analysis" '
                 sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://192.168.0.16:9000 -Dsonar.login=e04258ec57a7bf6f0a06d77b3cecbbb2ada4f7e1 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
        stage('Sonar Quality Gate'){
            steps{
                timeout(time: 1, unit: 'MINUTES')
                waitForQualityGate abortPipeline:true
                 sh 'echo "Starting Sonar Quality Gate" '              
            }
        }
        
    }
}

