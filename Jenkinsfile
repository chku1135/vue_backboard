pipeline {
    agent any
    environment {
        NAMESPACE = 'vue-project'
        // Jenkins Credentials ID: db-password
        DB_PASSWORD = credentials('db-password')
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
                // 백엔드 이미지 빌드 및 푸시
                sh 'docker build -t 192.168.56.13:80/vue-project/backend:latest .'
                sh 'docker push 192.168.56.13:80/vue-project/backend:latest'
            }
        }
        stage('Deploy') {
            steps {
                // 운영 DB 프로파일 및 패스워드 주입 배포
                sh "helm upgrade --install backend ./helm-chart -n ${NAMESPACE} --set env.DB_PASSWORD=${DB_PASSWORD} --set env.SPRING_PROFILES_ACTIVE=prd"
            }
        }
    }
}