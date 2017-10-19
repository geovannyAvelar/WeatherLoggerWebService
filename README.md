# WeatherLoggerWebService [![Build Status](https://travis-ci.org/geovannyAvelar/WeatherLoggerWebService.svg?branch=master)](https://travis-ci.org/geovannyAvelar/WeatherLoggerWebService) [![Issue Count](https://codeclimate.com/github/geovannyAvelar/WeatherLoggerWebService/badges/issue_count.svg)](https://codeclimate.com/github/geovannyAvelar/WeatherLoggerWebService) [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)

Arduino weather station project web service

## Summary
* 1 Build
    * 1.1 Pre-requisites
    * 1.2 Before build
        * 1.2.1 JDK version
        * 1.2.2 Database
    * 1.3 Build
* 2 License

## 1 Build

### 1.1 Pre-requisites

- [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) or [JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- [Git](https://git-scm.com/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)

### 1.2 Before build
#### 1.2.1 JDK version
This application can be build with JDK 7 or 8. By default, the compilation target is JDK 7, you can change it in pom.xml, just modify the tag **java.version** to 1.8.

#### 1.2.2 Database
This project use MySQL as relational database management system. You can obtain it [here](https://www.mysql.com/downloads/)

To configure the connection of SocialCRON with your MySQL database, open the file **src/main/resources/application.properties** and fill the properties with your username, password and database URL. The application can generate the database tables automatically, just set the property **spring.jpa.hibernate.ddl-auto** to **update**, don't use this in production.

### 1.3 Build

1. Get the source typing the command:
**git clone https://github.com/geovannyAvelar/WeatherLoggerWebService.git**

2. Enter on project directory:
**cd WeatherLoggerWebService**

3. With Maven, compile and package with:
**mvn install -DskipTests**

4. The depedencies will be resolved and a file named wlws-X.war will be generated on target directory, 'X' is the version name. Now you should deploy this .war file on a application server, like Tomcat.

## 2 License
This project is under The MIT License (MIT) terms.
