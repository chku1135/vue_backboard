pipeline {
    agent any
    environment {
        NAMESPACE = 'vue-project'
        // Jenkins Credentials ID: db-password
        DB_PASSWORD = credentials('db-password')
        // Harbor Login Credentials (ID: harbor-auth)
        HARBOR_CREDS = credentials('harbor-auth')
        KUBECONFIG_ID = 'k8s-config'
    }
    stages {
        stage('Build Backend') {
            steps {
                // 백엔드 Maven 빌드
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Docker Push') {
            steps {
                // Harbor 레지스트리 로그인
                // sh에서 $HARBOR_CREDS_USR, $HARBOR_CREDS_PSW가 자동 생성됨
                sh 'echo $HARBOR_CREDS_PSW | docker login 192.168.56.13:80 -u $HARBOR_CREDS_USR --password-stdin'
                
                // 백엔드 이미지 빌드 및 푸시
                sh 'docker build -t 192.168.56.13:80/vue-project/backend:latest .'
                sh 'docker push 192.168.56.13:80/vue-project/backend:latest'
            }
        }
        stage('Deploy') {
            steps {
                // 2. withKubeConfig 블록으로 감싸서 배포 수행
                withKubeConfig([credentialsId: "${KUBECONFIG_ID}"]) {
                    sh """
                    helm upgrade --install backend ./helm-chart \
                        -n ${NAMESPACE} \
                        --create-namespace \
                        --set env.DB_PASSWORD='${DB_PASSWORD}' \
                        --set env.SPRING_PROFILES_ACTIVE=prd
                    """
                }
            }
        }
    }
}