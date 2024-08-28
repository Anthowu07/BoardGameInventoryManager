pipeline {
    agent any

    environment {
        MAJOR_VERSION = '0'
        MINOR_VERSION = '0'
        PATCH_VERSION = "${env.BUILD_NUMBER}"

    }
    
    stages{

        stage('Prepare Version') {
            steps {
                script {
                    def newPatchVersion = PATCH_VERSION.toInteger() + 1
                    env.VERSION = "${MAJOR_VERSION}.${MINOR_VERSION}.${newPatchVersion}"
                    echo "Updated version to: ${env.VERSION}"
                }
            }
        }

        stage('Build and Analyze Frontend'){
            steps{
                dir('boardgameinventory-react'){
                    sh "echo Building Frontend"
                    sh "npm install && npm run build"
                    sh "mvn test"

                    withSonarQubeEnv('SonarCloud') {
                        sh '''
                            npx sonar-scanner \
                            -Dsonar.projectKey=anthowu07_boardgame-manager-frontend \
                            -Dsonar.projectName=boardgame-manager-frontend \
                            -Dsonar.sources=src \
                            -Dsonar.javascript.lcov.reportPaths=coverage/lcov.info
                        '''
                    }
                }
            }
        }

        stage('Test Frontend'){
            steps{
                dir('tests'){
                    sh "mvn test"
                }
            }
        }

        stage('Deploy Frontend'){
            steps{
                // script{
                //     try{
                //         withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                //             sh "aws s3 sync boardgameinventory-react/dist s3://boardgame-inventory-management"
                //             //sh "aws elasticbeanstalk create-application-version --application-name myName --version-label your-version-label 0.0.1 --source-bundle S3Bucket="boardgame-inventory-management",S3Key="*.jar""
                //             //sh "aws elasticbeanstalk update-environment --environment-name myName --version-label your-version-label"
                //         }
                //     }catch(Exception e){
                //         echo "${e}"
                //         throw e
                //     }
                // }
                script{
                      withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                        sh "aws s3 sync boardgameinventory-react/dist s3://boardgame-inventory-management"
                        //sh "aws s3 sync backend/src/main/resources/static s3://boardgame-inventory-management"
                        }  
                }
            }
        }

        stage('Build and Analyze Backend'){
            steps{
                dir('backend'){
                    sh "mvn clean install && ls target/"

                    withSonarQubeEnv('SonarCloud') {
                        sh '''
                        mvn sonar:sonar \
                            -Dsonar.projectKey=Anthowu07_BoardGameInventoryManager \
                            -Dsonar.projectName=boardgame-manager-backend \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        '''
                    }
                }
            }
        }

        stage('Test Backend'){
            steps{
                sh "cd backend && mvn test"
            }
        }

        stage('Deploy Backend'){
            steps{
                script{
                  withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
                        sh 'pwd'
                        sh "aws s3 cp backend/target/project1-0.0.1-SNAPSHOT.jar s3://boardgame-inventory-management-backend"
                        sh "aws elasticbeanstalk create-application-version --application-name boardgame-inventory --version-label ${VERSION} --source-bundle S3Bucket=\"boardgame-inventory-management-backend\",S3Key=\"project1-0.0.1-SNAPSHOT.jar\""
                        sh "aws elasticbeanstalk update-environment --environment-name Boardgame-inventory-env-4 --version-label ${VERSION}"
                    }  
                }   
            }
        }
    }
}
