pipeline {
    agent any

    stages{
        stage('Build Frontend'){
            steps{
                sh "echo Building Frontend"
                sh "cd boardgameinventory-react && npm install && npm run build"
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
                        }  
                }
            }
        }

        stage('Build Backend'){
            steps{
                sh "cd backend && mvn clean install && ls target/"
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
                        sh "echo 'aws elasticbeanstalk create-application-version --application-name boardgameManagement --version-label 0.0.1 --source-bundle S3Bucket=\"boardgame-inventory-management-backend\",S3Key=\"project1-0.0.1-SNAPSHOT.jar\"'"
                        sh "echo 'aws elasticbeanstalk update-environment --environment-name Boardgame-inventory-env-4 --version-label 0.0.1'"
                    }  
                }   
            }
        }
    }
}
