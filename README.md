# Restaurant App

## Description

The **Restaurant App** is a web-based application designed to streamline the management of a restaurant. It allows staff to manage tables, reservations, menus, and events. The app also provides customers with the ability to make reservations and view the restaurant's menu online.

This project is built using **Java** with **JSP** for the frontend and **Maven** for dependency management. The app connects to a **MySQL** database to store and manage data related to reservations, tables, menus, and events.

## Features

- **Table Management**: Staff can manage table availability and assign reservations to specific tables.
- **Menu Management**: Staff can add, update, and remove menu items.
- **Event Management**: Staff can create and manage restaurant events, view upcoming events, and delete past or canceled events.
- **Reservation System**: Customers can make reservations, and staff can view and manage these reservations.
- **User Management**: Admins can manage users and their roles.
- **Staff Management**: Admins can manage staff and their roles.
- **Error Management**: Allows customers to report issues, and staff to view and update error statuses.
-** Feedback Management**: Enables customers to submit feedback and staff to respond to it.
- **Order Management**: Facilitates order creation, tracking, and status updates by staff.
- **Payment Management**: Supports multiple payment methods with the ability to cancel or view payment history.
- **Inventory Management**: Monitors stock levels, updates inventory, and provides low stock notifications.

## Tech Stack

- **Frontend**: JSP (JavaServer Pages), HTML, CSS
- **Backend**: Java, Servlets
- **Database**: MySQL
- **Build Tool**: Maven
- **Testing**: JUnit, Mockito
- **Server**: Jetty (for local development)
- **CI/CD**: Azure DevOps Pipelines

## Setup/Installation

To run this project locally, follow the steps below:

### Prerequisites
- **Java**: Version 19 or higher.
- **Maven**: Version 3.x.
- **MySQL**: Database set up with the schema found in the `/sql` folder.
- **Jetty Server** (comes with Maven).

### Steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/restaurant-app.git
    cd restaurant-app
    ```

2. **Set up the MySQL database**:
    - Import the provided SQL file (`/sql/restaurantdb.sql`) into your MySQL database.
    - Update the MySQL connection details in the `DBConnector` class (`src/main/java/DAO/DBConnector.java`).

3. **Build the project**:
    ```bash
    mvn clean install
    ```

4. **Run the application**:
    ```bash
    mvn jetty:run
    ```

5. **Access the application**:
    Open your browser and navigate to `http://localhost:8080`.

## Continuous Integration (CI/CD)

This project is integrated with **Azure DevOps Pipelines** for continuous integration and deployment.

## Project Structure

- **src**
  - **main**
    - **java**
      - **DAO**: Data Access Objects for database interaction
      - **model**: Java models for Menu, Table, Event, etc.
      - **controller**: Servlets for handling HTTP requests
    - **webapp**
      - **jsp**: JSP files for frontend views
      - **css**: CSS files for styling
  - **test**: Unit tests
- **pom.xml**: Maven configuration file
- **.yml**: Azure DevOps pipeline configuration
- **README.md**: Project documentation



## SQL Tables

- users
- menu
- events
- tables
- resrevations
- orders
- inventory
- payements
- errors
- feedback
- staff

### .yml Pipeline Configuration:

```yaml
trigger:
  - "*"

pool:
  vmImage: ubuntu-latest

variables:
  buildConfiguration: "Release"

steps:
  - script: |
      sudo apt-get update
      sudo apt-get install -y openjdk-19-jdk
    displayName: "Install OpenJDK 19"

  - task: Maven@4
    displayName: "Maven - Restore Dependencies"
    inputs:
      mavenPomFile: "pom.xml"
      goals: "dependency:resolve"

  - task: Maven@4
    displayName: "Maven - Build"
    inputs:
      mavenPomFile: "pom.xml"
      goals: "clean install"

  - task: Maven@4
    displayName: 'Run unit tests'
    inputs:
      mavenPomFile: "pom.xml"
      goals: "test"
      options: "-DskipTests=false"

  - task: PublishTestResults@2
    displayName: 'Publish Test Results'
    inputs:
      testResultsFiles: '**/target/surefire-reports/*.xml'
      mergeTestResults: true

  - script: |
      zip -r $(Build.ArtifactStagingDirectory)/artifact.zip $(System.DefaultWorkingDirectory)/target
    displayName: "Create ZIP File of Build Artifacts"

  - task: PublishBuildArtifacts@1
    displayName: "Publish Artifacts"
    inputs:
      pathtoPublish: "$(Build.ArtifactStagingDirectory)/artifact.zip"
      artifactName: "drop"
      publishLocation: "Container"

