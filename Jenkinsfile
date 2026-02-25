pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '/opt/homebrew/Cellar/maven/3.9.11/bin/mvn -B clean test'
            }
        }

        stage('Package') {
            steps {
                sh '/opt/homebrew/Cellar/maven/3.9.11/bin/mvn -B -DskipTests package'
            }
        }

        stage('Docker Build') {
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker build -t todolist-demo:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Run') {
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker run --rm todolist-demo:${BUILD_NUMBER}'
            }
        }
    }
}
