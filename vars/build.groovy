echo 'building'
sh 'whoami'
sh '''
export PATH=$PATH:/usr/local/bin:/Applications/Docker.app/Contents/Resources/bin
docker build -t notes-app-demo:latest .
'''
