def call(Map pipelineParams) {
    pipeline {
        agent any

        environment {
            dockerhub = credentials('DockerHub')
        }

        stages {
            stage('Docker Build') {
                steps {
                    script {
                        dockerLib.build(DockerfilePath: pipelineParams.dockerfilePath,
                                        DockerImage: pipelineParams.dockerImage,
                                        DockerContext: pipelineParams.dockerContext)
                    }
                }
            }

            stage('Docker Login') {
                steps {
                    script {
                        dockerLib.login(DockerUser: dockerhub_USR,
                                        DockerPass: dockerhub_PSW)
                    }
                }
            }

            stage('Docker Push') {
                steps {
                    script {
                        dockerLib.push(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }

            stage('Docker Logout') {
                steps {
                    script {
                        dockerLib.logout()
                    }
                }
            }
        }
    }
}
