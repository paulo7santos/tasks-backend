pipeline {
    agent any
    stages{
        stage('Backend Build'){
            steps{
                 sh 'echo "Building backend" '
                 sh 'mvn clean package -DskipTests=true'
            }
        }
    }
}