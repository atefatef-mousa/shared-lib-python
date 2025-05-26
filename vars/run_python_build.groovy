def call() {
pipeline{
    agent{
        label "agent_1"
    }
    environment{
        DOCKER_USER = credentials('dockerhub-user')
        DOCKER_PASS = credentials('dockerhub-password')
    }
    stages{
        stage("build Docker image"){
            steps{
                script{
                    def dockerx = new org.iti.docker()
                    dockerx.build("python_build_with_global", "${BUILD_NUMBER}")
                }
            }
 
               
            
        }
        stage("Push Docker image"){
            steps{
                script{
                    def dockerx = new org.iti.docker()
                    dockerx.login("${DOCKER_USER}", "${DOCKER_PASS}")
                    dockerx.push("python_build_with_global","${DOCKER_USER}", "${BUILD_NUMBER}")
                }
            }
        }
    }
}




}