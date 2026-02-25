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
                sh 'mvn clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -DskipTests package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t todolist-demo:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run --rm todolist-demo:${BUILD_NUMBER}'
            }
        }
    }
}
