# Project Hydro - Skincare Inventory ERP

A Spring Boot backend application for managing skincare products, suppliers, warehouses, batches, and purchase orders.

## Features

- Manage product categories
- Manage skincare products
- Manage suppliers and customers
- Manage warehouses
- Track product batches and expiry dates
- Create purchase-order headers
- Add products as purchase-order line items
- Calculate purchase-order line totals
- Validate incoming API requests
- Handle missing database records
- Store data in MySQL

## Technology

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Jakarta Bean Validation
- MySQL
- Maven
- Postman

## Project Structure

- `controller` - REST API endpoints
- `service` - Business logic
- `repository` - Database access using Spring Data JPA
- `entity` - JPA database entities and relationships
- `dto` - API request and response objects

## API Resources

- `/api/categories`
- `/api/products`
- `/api/suppliers`
- `/api/customers`
- `/api/warehouses`
- `/api/batches`
- `/api/purchase-orders`
- `/api/purchase-order-items`

## Database Setup

Create a MySQL database for the application:

```sql
CREATE DATABASE skincare_inventory_erp;
```

Configure the database connection in:

```text
src/main/resources/application.properties
```

The database password is supplied through an environment variable:

```properties
spring.datasource.password=${DB_PASSWORD}
```

## Run the Application

Set the `DB_PASSWORD` environment variable to your local MySQL password.

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The REST API runs at:

```text
http://localhost:8080
```

## Project Status

Project Hydro is currently under development.

Completed milestones:

- Master data
- Product batch tracking
- Purchase-order headers
- Purchase-order line items

Planned milestones:

- Goods receipts
- Inventory movements
- Stock-level tracking
- Authentication and authorization
- Automated testing
