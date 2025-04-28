pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'main', url: 'https://github.com/JayLikhare316/project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t jaylikhare/project-backend-img:latest
                    docker push jaylikhare/project-backend-img:latest
                    docker rmi jaylikhare/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}