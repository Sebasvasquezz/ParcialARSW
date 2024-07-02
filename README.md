
# Parcial

This project contains an application to consult the stock market for shares traded on the Stock Exchange.  The application receives the identifier of a stock, for example “MSFT” for Microsoft and must show the history of the intraday, daily, weekly and monthly valuation. For this, the free API at https://www.alphavantage.co/documentation. 

### Characteristics
1. Client written in React invoking REST services 
2. Facade server implemented in Spring exposing REST services 
3. Connection to external services
4. Application deployed on AWS 
5. Java client for concurrent testing 
6. Concurrency-tolerant cache

## Getting Started
Download the project from 
[the repository.](https://github.com/Sebasvasquezz/ParcialARSW)

You can also clone the file using the following command.

```
git clone https://github.com/Sebasvasquezz/ParcialARSW  
```

### Prerequisites

* [Maven](https://maven.apache.org/): Automate and standardize the life flow of software construction

* [Git](https://www.git-scm.com/): Decentralized Configuration Manager

* [Node](https://nodejs.org/en/): A JavaScript runtime built on Chrome's V8 engine, enabling server-side scripting and development of scalable network applications.

### Installing
1. Maven
    * Download Maven in http://maven.apache.org/download.html
    * You need to have Java installed (7 or 8)
    * Follow the instructions in http://maven.apache.org/download.html#Installation

2. Git
    * Download git in https://git-scm.com/download/win
    * Follow the instructions in https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

3. Node
    * Download Node in https://nodejs.org/en
    * Follow the instructions in https://nodejs.org/en/learn/getting-started/how-to-install-nodejs

### Installing

Once you have the cloned project in your repository. Follow the steps below to launch the program successfully.

#### Run Spring-boot

1. Open a terminal and enter the folder where I clone the repository and enter the BoardSpring folder and go to the target folder.

2. Use the following command to removes files generated in previous builds, compiles the code and packages the project into a JAR or WAR file ready for distribution.
    ```
    mvn clean package
    ```
3. Now you can run the project using the following command.

    ```
    java -jar <documento jar>
    ```

4. Now open a browser and go to the following [link](http://ec2-3-91-249-28.compute-1.amazonaws.com:8080/) to start drawing:
![Execution in AWS](image.png)



## Architectural Design

![Architectural Design](<Blank diagram.png>)

This architecture diagram illustrates an AWS-based web application with the following main components:

* **AWS General User:** The user who accesses the web application from a browser.

* **JsWebClient:** Browser (port: 3000): The web client that contains:
  * HTML: The structure of the web page.
  * JS (JavaScript): The JavaScript code that handles the frontend logic.
  * React: The JavaScript library for building user interfaces.
  * Axios: A library for making HTTP requests from the frontend. This web client communicates with the server on port 8080 using the HTTP-JSON protocol.
* **EC2:** An EC2 instance on AWS that hosts a Spring-based application inside a JVM.
    * Spring: An application framework for Java.
    * Controller: The controller that handles HTTP requests and coordinates the application logic.
    * Model: The model layer that contains the business logic and data. This instance communicates with the web client and the Java test client on port 8080 using HTTP-JSON.
* External REST API: An external REST API that the Spring application communicates with on port 80 using HTTP-JSON.
* Java Test Client: A test client that also communicates with the Spring application on port 8080 using HTTP-JSON.
In short, the data flow begins with the general AWS user interacting with the web client in the browser. This web client sends HTTP-JSON requests to the EC2 instance running the Spring application. The Spring application handles these requests and can communicate with an external REST API or respond to the web client or Java test client as needed.
## Built with

* [Maven](https://maven.apache.org/) - Dependency management
* [Node](https://nodejs.org/en/) - JavaScript runtime for building scalable network applications.

## Authors

* **Juan Sebastian Vasquez Vega**  - [Sebasvasquezz](https://github.com/Sebasvasquezz)

## Date

Jule 2, 2024

## License

This project is licensed under the GNU License - see the [LICENSE.txt](LICENSE.txt) file for details.