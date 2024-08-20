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

        // stage('Build Backend'){
        //     steps{
        //         sh "cd backend && mvn clean install"
        //     }
        // }

        // stage('Test Backend'){
        //     steps{
        //         sh "cd backend && mvn test"
        //     }
        // }

        // stage('Deploy Backend'){
        //     steps{
        //         script{
        //           withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS'){
        //                 sh 'pwd'
        //                 sh "aws s3 cp demo/target/demo-1.0-SNAPSHOT.jar s3://bjgomes-bucket-sdet-backend"
        //                 sh "echo 'aws elasticbeanstalk create-application-version --application-name myName --version-label 0.0.1 --source-bundle S3Bucket=\"bjgomes-bucket-sdet-backend\",S3Key=\"demo-1.0-SNAPSHOT.jar\"'"
        //                 sh "ech 'aws elasticbeanstalk update-environment --environment-name myName --version-label 0.0.1'"
        //             }  
        //         }   
        //     }
        // }
    }
}
