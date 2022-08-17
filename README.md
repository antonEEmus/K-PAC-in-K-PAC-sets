# K-PAC in K-PAC sets

`A WEB application that supports CRUD operations with entities and displaying them in grids on the user side.`

---

### Project structure

This app operates with 2 types of entities:
1. K-PAC (Knowledge Package) - an entity that stores contents of any knowledge;
2. K-PAC Set - an entity that can contain multiple K-PACs.

The application is developed based on N-tier architecture, which contains of 3 levels:
1. Controller - the level that receives requests from client, handles them, calls service methods and sends the response to the client.
2. Service - the level that contains the business logic and call DAO methods to perform operations with the database.
3. DAO (Data Access Object) - the level that works with database and performs the CRUD operations.

After the data is passed to the client, it is viewed on JSP pages with DHTMLX grids.

---

### Useful endpoints

- `/index` - main page. Contains links to other pages.
- `/kpacs` - page that displays all the K-PACs stored in the database. 
There are abilities to filter the data with extra form, to sort the data by clicking on the header of the column, 
and to delete records from the database by clicking on the delete icon.
- `/kpacs/add` - page to add a new K-PAC record to the database with an HTML form.
- `/sets` - page that displays all the K-PAC Sets stored in the database.
    There are abilities, same as on `/kpacs`.
- `/sets/add` - page to add a new K-PAC Set record to the database with an HTML form.
- `/set/{id}` - page that displays all the K-PAC of a certain set, which id is passed as a path variable.

---

### Developed with:

- Java 11, Hibernate 5, Spring 5, Spring JDBC, Spring MVC
- MySQL 8
- Lombok, Jackson, Hibernate validator
- Maven, Intellij IDEA
- JavaScript, DHTMLX7, CSS, JSP, JSTL
- Apache Tomcat 9

---

### How to run this project

1. Clone this project and open it (preferably in Intellij IDEA)
2. Run /resources/schema.sql in your DBMS tool (IDE/Workbench/CLI) to create the MySQL schema and tables
3. Run /resources/data.sql to fill the database with test data
4. Write your database username and password in /resources/application.properties
5. Add a Tomcat 9 run configuration
6. Run the project with Tomcat
