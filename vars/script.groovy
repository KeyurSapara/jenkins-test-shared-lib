stages {
    stage('code') {
        steps {
            echo 'code part'
            git url: 'https://github.com/KeyurSapara/TWS-django-notes-app-fork.git', branch: 'main'
            echo 'successfully cloned'
        }
    }

    stage('build') {
        steps {
            echo 'building'
            sh 'whoami'
            sh '''
            export PATH=$PATH:/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin
            docker build -t notes-app-demo:latest .
            '''
        }
    }

    stage('push to docker repo') {
        steps {
            echo 'docker hub push'
            withCredentials([
                usernamePassword(
                    credentialsId: "docker-creds",
                    usernameVariable: "dockerHubUser",
                    passwordVariable: "dockerHubPass"
                )
            ]) {
                sh '''
                export PATH=$PATH:/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin
                docker login -u $dockerHubUser -p $dockerHubPass
                docker tag notes-app-demo:latest $dockerHubUser/notes-app-demo:latest
                docker push $dockerHubUser/notes-app-demo:latest
                '''
            }
        }
    }

    stage('deploy') {
        steps {
            echo 'deployment'
            sh '''
            export PATH=$PATH:/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin
            docker compose up -d
            '''
        }
    }
}
