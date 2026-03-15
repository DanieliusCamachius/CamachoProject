pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

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
                sh "mvn test -Dcucumber.filter.tags='@regression'"
                // ou: sh "mvn test -Dtest=RunCucumberTest"
            }
        }

        stage('Run CucumberFruits Feature') {
            steps {
                sh "mvn -Dtest=TestRunnerFruitTable test -Dcucumber.filter.tags='@Fruits'"
                // ou: sh "mvn test -Dtest=RunCucumberTest"
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
                cucumber fileIncludePattern: 'target/cucumber-reports/*.json'
            }
        }
    }
}
