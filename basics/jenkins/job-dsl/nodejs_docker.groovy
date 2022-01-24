job('NodeJS Docker example') {
    scm {
        git('https://github.com/slavapa/docker-cicd.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs-new') 
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('slavapas13/nodejs-jb-demo-14')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('6726da79-3a75-4e06-b9ac-4c4c8d574d94')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
