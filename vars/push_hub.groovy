def call () {
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
