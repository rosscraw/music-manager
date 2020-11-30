# Getting Started

## How To Run Me

### Step1 - MySQL Database Setup

* Start a MySQL server on <b>localhost:3306</b>
* Create a Schema named <b>"music_manager"</b>
* Update the password for the database in both <b>"src\main\resources\application.properties"</b> and <b>"src\main\java\com\dataloader\DBConnection.java"</b>.
* Data should be inserted once Spring Boot Application is run.

### Step2 - Install

At the top of the project run an install to download the relevant dependencies and compile the code

<b><i>./mvnw install</b></i>

### Step3 - Run

At the top of the project start spring boot which will run at http://localhost:8080/
<b><i>./mvnw spring-boot:run</b></i>

You will then be shown a login screen and can log in using one of the username and password combinations that was present in the data text file.
