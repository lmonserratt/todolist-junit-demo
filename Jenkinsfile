pipeline {
    agent any

    environment {
        // Use a clean Docker config for Jenkins so it doesn't look for docker-credential-desktop
        DOCKER_CONFIG = "${WORKSPACE}/.docker"
    }

    stages {

        stage('Checkout') {
            steps { checkout scm }
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
                sh '''
                    mkdir -p "$DOCKER_CONFIG"
                    # Create a minimal docker config WITHOUT "credsStore"
                    cat > "$DOCKER_CONFIG/config.json" <<'JSON'
                    { "auths": {} }
JSON
                    /Applications/Docker.app/Contents/Resources/bin/docker build -t todolist-demo:${BUILD_NUMBER} .
                '''
            }
        }

        stage('Docker Run') {
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker run --rm todolist-demo:${BUILD_NUMBER}'
            }
        }
    }
}
