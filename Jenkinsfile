pipeline {
    agent any
    stages{
        
        stage('Deploy Prod'){
            steps{
                dockerNode('docker') {

                    docker run hello-world
                    //sh 'docker-compose build'
                    //sh 'docker-compose up -d'
                }
               
            }
        }   
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
                sleep(5)
                timeout(time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline:true
                sh 'echo "Starting Sonar Quality Gate" '        
                }      
            }
        }
        stage('Deploy Backend'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://192.168.0.16:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'      
            }
        } 
        stage('API Test'){
            steps{
                dir('api-test') {
                    git credentialsId: 'github_login', url: 'https://github.com/paulo7santos/tasks-api-test.git'    
                    sh 'echo "Starting API Tests" '
                    sh 'mvn test' 
                }
            }
        }
        stage('Deploy Frontend'){
            steps{
                dir('frontend') {
                    git credentialsId: 'github_login', url: 'https://github.com/paulo7santos/tasks-frontend.git'
                    sh 'echo Starting Frontned Buid'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://192.168.0.16:8001/')], contextPath: 'tasks', war: 'target/tasks.war'      
                }
            }   
        } 
        stage('Functional Test'){
            steps{
                dir('functional-test') {
                    git credentialsId: 'github_login', url: 'https://github.com/paulo7santos/tasks-functional-test'
                    sh 'echo Starting Frontend Buid'
                    sh 'mvn clean test'
                }
            }   
        }
    }
}




