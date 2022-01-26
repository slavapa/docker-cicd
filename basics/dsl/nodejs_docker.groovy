job('NodeJS Docker example') {
    scm {
        git('https://github.com/slavapa/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Slava')
            node / gitConfigEmail('jslavapas13@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('yanivomc/amdocsapp')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('yaniv-dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

