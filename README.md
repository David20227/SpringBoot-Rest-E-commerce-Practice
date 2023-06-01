# SpringBoot-Rest-E-commerce-Practice

## Deploying a Spring Boot Application (incompleted for now)

This guide provides an overview of how to deploy a Spring Boot application and explains its functionality.

### Application Overview

The Spring Boot application is a web application that manages categories and products. It provides the following functionality:

1. Retrieving all categories: The application allows users to retrieve a list of all available categories.

2. Retrieving products by category: Users can fetch a list of products associated with a specific category.

### Deployment Steps

To deploy the Spring Boot application, follow these steps:

1. Build the application: Use a build tool like Maven or Gradle to build the application. Navigate to the root directory of the project containing the `pom.xml` file, and execute the build command. For example, using Maven:

   ```shell
   mvn clean package

This command will clean and compile the application, run tests, and package it into a JAR file.

Set up the environment: Ensure that you have a compatible Java Runtime Environment (JRE) installed on the deployment machine.

Deploy the application: Once the build is successful, deploy the application to a server of your choice. You can deploy it as a standalone JAR file or deploy it to a servlet container like Apache Tomcat.

Configure the application: If required, configure the application by modifying the application.properties or application.yml file. This file contains properties such as database connection details or server port.

Start the application: Start the deployed application by running the JAR file or starting the servlet container.

Access the application: Once the application is up and running, you can access it by navigating to the appropriate URL. Use the defined endpoints to retrieve categories or products as needed.
