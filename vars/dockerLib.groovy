def build(Map params) {
    sh "docker build -f ${params.DockerfilePath} -t ${params.DockerImage} ${params.DockerContext}"
}

def login(Map params) {
    sh "echo ${params.DockerPass} | docker login -u ${params.DockerUser} --password-stdin"
}

def push(Map params) {
    sh "docker push ${params.DockerImage}"
}

def logout() {
    sh 'docker logout'
}
