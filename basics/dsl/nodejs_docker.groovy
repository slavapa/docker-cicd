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
            repositoryName('slavapas13/docker-cicd')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('a68981a1-6410-4131-a097-e4b1fb7657c2')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

