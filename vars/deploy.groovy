def call () {
    echo 'deployment'
    sh '''
    export PATH=$PATH:/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin
    docker compose up -d
    '''
}
