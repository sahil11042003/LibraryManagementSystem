The Library Management System is a back-end project which is a robust and efficient Java-based project that leverages various technologies such as 
Maven, Hibernate, JPA, Redis, Spring Security, and MySQL to provide comprehensive CRUD (Create, Read, Update, Delete) operations via RESTful APIs for managing authors, books, transactions, and students. 
The User entity is designed to have one authority assigned to it during user creation, either "student" or "admin." 
The "admin" authority grants higher privileges, allowing administrators to manage user accounts, add new books, and configure library settings.
On the other hand, users with the "student" authority have limited access, allowing them to perform basic functions like borrowing books and updating their profile’s.
The project follows a structured architecture with multiple layers, including entity, controller, service, and configuration.

Key Features:

Technology Stack:

•	Java: The backend is developed using Java, a widely used and powerful programming language.
•	Maven: It facilitates project management, dependency resolution, and build automation.
•	Hibernate and JPA: These technologies are used for object-relational mapping, making it easier to interact with the MySQL database.
•	Redis: A fast and in-memory data store is used for caching frequently accessed data, improving system performance.
•	Spring Security: Ensures the application is secure by providing authentication and authorization mechanisms.
•	MySQL Database: Serves as the persistent data storage for the Library Management System.

RESTful APIs:

•	The backend provides RESTful APIs to enable CRUD operations for the following entities:

•	Author: APIs to manage author information, including creation, retrieval, updating, and deletion.
•	Book: APIs to handle book-related actions such as adding new books, retrieving book details, updating book information, and deleting books.
•	Transaction: APIs to manage the borrowing and returning of books by students.
•	Student: APIs to manage student data, including registration, profile updates, and removal.
•	User: API to create an user.

Layered Architecture:

•	Entity Layer: Represents the data models using Java classes and annotations for Hibernate mapping.
•	Controller Layer: Handles incoming requests, processes the data, and sends responses back to the clients.
•	Service Layer: Contains business logic, validates input data, and interacts with the database via JPA/Hibernate.
•	Repository Layer: Acts as an abstraction between the application's business logic and the underlying data storage, providing standardized methods for data access and manipulation. 
•	DTO Layer: It provides lightweight objects for data transformation, validation, security, and optimizing data transfer between backend and frontend components.
•	Configuration Layer: Configures various components of the application, including Spring Security settings, Redis caching, and database connectivity.

Security:

•	Spring Security is implemented to ensure secure access to the RESTful APIs. 
•	Users need to authenticate before accessing specific endpoints, and authorization is enforced to restrict access based on user roles.

Redis Caching:

•	To optimize performance and reduce database load, the User entity is stored in Redis cache, a fast and in-memory data store. 
•	By caching user information, authentication details, and role-based access permissions, the system ensures quicker access to frequently requested user data, enhancing overall responsiveness and scalability of the Library Management System.
