## Java Spring boot project for backend content and post-management API

The backend application has components such as model, DTO,
service, repository, controller, and Security
* The model directory has entities that represent the database table objects.
* The DTO directory contains, DTOs that are used in the backed application to transfer data between processes.
* The service directory holds service classes that are defined to implement business
logic. The methods defined in the service class are called by the controller class
and execute database crude operations using repository classes.
* The repository folder has an entity class-specific repository interface to implement
the data access layer. The repository class extends the JPARepository interface and
takes the entity model name and ID attribute as parameters. The repositories that
are defined use the method name directly to get store-specific queries that are
part of JPARepository methods for the purpose of CRUD operations.
* Controller directories have REST controllers to perform get, post, delete, and put
requests. The controller classes expose the API endpoint that will be used by the
frontend application.
* The "security" folder contains classes that use the Spring Security framework to
authenticate requests made to access the endpoints defined inside controller classes. The "AuthenticationEntryPoint" class is a Spring Security implementation that
catches unauthorized requests and returns a 401-response code when an endpoint is accessed without proper authentication

## Technologies Used:
* Spring Boot: A Java-based framework for building scalable and maintainable backend applications.
* Spring Security: Used for authentication and authorization to ensure secure access control for users.
* JPA/Hibernate: Used for ORM (Object-Relational Mapping) to interact with the database.
* REST API: Exposes endpoints for managing contacts in a stateless manner.
* MySQL: A relational database used for storing and managing contact data.
