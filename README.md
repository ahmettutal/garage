# Garage

This is a Garage Project developed with spring-boot

The Garage is an automated ticketing system for a garage that allows it to be used without human intervention. When a car enters the garage, the driver is given
a unique ticket. The customer vehicle is allocated to the slot(s) closest to the entrance. The ticket is refundable on exit.

## Getting Started

Here are the instructions to set up the project and run it on your local machine for testing purposes. It is assumed that the necessary configurations have been
made for the database (such as creating a database named garage).

### System Requirements

- Java ^8
- Maven
- Git (for version control)

### Built With

- mvn clean install (About [Maven](https://maven.apache.org/))

### Running

- mvn spring-boot:run

## REST API overview

The Garage provides REST APIs that include these endpoints:

- `/park` -> Parks Vehicle
- `/leave` -> Leaves Garage
- `/status` -> Garage Status

You can use Postman to target the REST APIs. The Postman collection file is available in the
[`src/main/resources`](src/main/resources)
directory.

## License

Apache 2.0
