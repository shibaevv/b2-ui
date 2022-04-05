# Introduction
Book a Banker

# Getting Started

- [http://localhost:8080](http://localhost:8080)

    mvn spring-boot:run
    mvn spring-boot:run -Dspring-boot.run.profiles=local
    mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=80 --server.ssl.enabled=false --spring.security.require-ssl=false"

- [https://localhost:8443](https://localhost:8443)

    mvn spring-boot:run -Dspring-boot.run.profiles=ssl
    mvn spring-boot:run -Dspring-boot.run.profiles=

### Containerize MSA:
Builds an image and tags it
image is built using Cloud Native Buildpacks (no need for Dockerfile):

    mvn spring-boot:build-image

### Run MSA via docker:
    docker run -it -p80:8080 --name b2-ui b2-ui:1.0.0-SNAPSHOT
    docker container stop b2-ui
    docker container rm b2-ui


# Build the application and push the container to Azure Container Registry
    az acr login -n <registry> && mvn package jib:build

# [Azure App Service] Configure the deployment
    export REGISTRY_NAME=vcdev
    export REGISTRY_USERNAME=vcdev
    export REGISTRY_PASSWORD=???
    export SPRING_PROFILES_ACTIVE=local
    az acr login -n $REGISTRY_NAME && mvn package jib:build
    #
    # Build to Docker daemon
    mvn package jib:dockerBuild
    docker run -it -p80:8080 --name b2-ui $REGISTRY_NAME.azurecr.io/b2-ui
    docker run -e "SPRING_PROFILES_ACTIVE=ssl" -it -p443:8443 --name b2-ui $REGISTRY_NAME.azurecr.io/b2-ui
    #
    # Build an image tarball
    mvn package jib:buildTar
    docker load --input target/jib-image.tar
    
- [Deploy a Spring Boot application to Linux on Azure App Service](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-linux)

# [App Service] Configure the deployment
    az login
    az account list-locations -o table
    # mvn com.microsoft.azure:azure-webapp-maven-plugin:1.13.0:config
    mvn package azure-webapp:deploy
    az group delete --name <resource_group_name>
    
- [Quickstart: Create a Java app on Azure App Service](https://docs.microsoft.com/en-gb/azure/app-service/quickstart-java?pivots=platform-linux&tabs=javase)

# References
- [Zulu OpenJDK for Azure Docker images](https://hub.docker.com/_/microsoft-java-jdk)
- [Deploy a Spring Boot application to Linux on Azure App Service](https://docs.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-linux)
- [Jib - Containerize your Maven project](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)

# Java 11
    brew install openjdk@11
    sudo ln -sfn $(brew --prefix)/opt/openjdk@11/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-11.jdk
    