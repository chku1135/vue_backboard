pipeline {
    agent any
    tools {
        jdk 'JDK-17'
    }
    environment {
        NAMESPACE = 'vue-project'
        // Harbor Login Credentials (ID: harbor-auth)
        HARBOR_CREDS = credentials('harbor-auth')
    }
    stages {
        stage('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'harbor-auth', passwordVariable: 'HARBOR_PW', usernameVariable: 'HARBOR_USER')]) {
                //sh 'echo $HARBOR_CREDS_PSW | docker login 192.168.56.13:80 -u $HARBOR_CREDS_USR --password-stdin'
                //sh 'echo $HARBOR_CREDS_PSW | docker login 192.168.56.13 -u $HARBOR_CREDS_USR -p $HARBOR_CREDS_PSW'
                sh 'echo $HARBOR_PW | docker login 192.168.56.13:80 -u $HARBOR_USER --password-stdin'
                sh 'docker build -t 192.168.56.13:80/vue-project/backend:latest .'
                sh 'docker push 192.168.56.13:80/vue-project/backend:latest'
                }
            }
        }
        stage('Deploy') {
            steps {
                // 'k8s-config'라는 ID의 Credentials를 파일로 가져와서 사용
                withCredentials([file(credentialsId: 'k8s-config', variable: 'KUBECONFIG')]) {
                //withEnv(['KUBECONFIG=/var/lib/jenkins/.kube/config']) {
                    // --set deployment.timestamp=\$(date +%s)로 강제 재배포 유도
                    sh """
                    helm upgrade --install backend ./helm-chart -n ${NAMESPACE} \
                        --set env.SPRING_PROFILES_ACTIVE=prd \
                        --set imagePullSecrets[0].name=harbor-registry-secret \
                        --set deployment.timestamp=\$(date +%s)
                    """
                }
            }
        }
    }
}
