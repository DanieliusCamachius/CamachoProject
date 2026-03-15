pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Run Cucumber Tests With Tag') {
            steps {
                sh "mvn -PCucumberTests test -Dcucumber.filter.tags='@Regression'"
            }
        }

        stage('Run CucumberFruits Feature') {
            steps {
                sh "mvn -PCucumberTests -Dtest=TestRunnerFruitTable test"
            }
        }

        stage('Run CucumberShop Feature') {
            steps {
                sh "mvn -PCucumberTests -Dtest=TestRunner test"
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
