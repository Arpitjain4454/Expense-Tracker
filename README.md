# Deploy Link - http://13.53.234.22:8080/swagger-ui/index.html#/

# Expense Tracker API
#### Expense Tracker is a RESTful API that allows users to track their expenses. Users can create, read, update, and delete expenses, and generate reports on a monthly or weekly basis. Users must sign in or register to access the API.

# Getting Started
#### To get started with the Expense Tracker API, you'll need to have the following installed:

- Java 8 or later
- Maven
- MySQL
#### Once you have these installed, you can clone the repository and run the following command to start the application:

#### Here are the API endpoints

- `POST /api/users`: Create a new user.
- `GET /api/products?username={username}&date={date}&time={time}`: Retrieve all products for a particular date and time.
- `GET /api/expenditure?username={username}&month={month}`: Retrieve the total expenditure for a given month.
- `POST /api/products`: Create a new product.
- `DELETE /api/products/{id}`: Delete a product by ID.

Request Body for login:

`{
-  "username": "john.doe",
- "password": "password"
}`

- `GET /api/products?username={username}&date={date}&time={time}`
Retrieve all products for a particular date and time.

### Query Parameters:

- username: the username of the user
date: the date of the products in yyyy-MM-dd format
time (optional): the time of the products in HH:mm:ss format
- `GET /api/expenditure?username={username}&month={month}` - Retrieve the total expenditure for a given month.

### Query Parameters:

- username: the username of the user
- month: the month in yyyy-MM format
- POST /api/products

### Create a new product.

### Request Body:

- `DELETE /api/products/{id}` - Delete a product by ID.

### Path Parameters:

- id: the ID of the product to delete

## Technologies Used
#### Expense Tracker is built using the following technologies:

- Java
- Spring Boot
- Hibernate
- MySQL
- Maven


# Project Summary:

##### The Expense Tracker API is a RESTful API that allows users to track their expenses. It provides CRUD operations for expenses, and also allows users to generate reports on a monthly or weekly basis. Users must sign in or register to access the API.

##### The API is built using the Spring Boot framework and Java language. It uses a MySQL database to store user and expense data. The API has a model, service, controller, and repository layer, which have been implemented using best practices in software development.

##### Users can create, read, update, and delete expenses using the API. They can also generate reports on a monthly or weekly basis. The API calculates the total expenditure for a given month and displays it to the user.


# Contributing
- Contributions to Expense Tracker are welcome! If you would like to contribute, please fork the repository and submit a pull request.

# Video Presentation

