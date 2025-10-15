# 🛒 E-Commerce REST API (Spring Boot)

A real-world, production-ready eCommerce backend built using **Spring Boot**, designed to power web and mobile frontends.

---

## 📦 Features

### 🧑 User Module
- User registration & login (JWT-based authentication)
- Role-based access (`USER`, `ADMIN`)
- Profile management
- Email verification
- Password reset

### 🛍️ Product Module
- Product CRUD operations (Admin only)
- Product listing & filtering
- Product categories
- Product image support

### 🏷️ Category Module
- Category CRUD (Admin)
- Category listing & filtering

### 🏬 Brand Module
- Brand CRUD (Admin)
- Brand listing & filtering

### 🛒 Cart & Wishlist
- Add/remove products to cart
- Modify item quantities
- Wishlist management

### 💰 Checkout & Orders
- Address management
- Order placement & order history
- Order tracking & status updates
- Cancel order functionality

### 🧑‍💻 Admin Panel
- Manage products, users, categories, and brands
- View and manage all orders
- Activate/deactivate user accounts
- Dashboard metrics (sales, users, inventory)
- Update order statuses

### 🔐 Security
- JWT-based authentication
- Spring Security with role-based access
- CORS enabled for frontend integration

---

## 🚀 Tech Stack

| Layer             | Technology                    |
|-------------------|--------------------------------|
| Framework         | Spring Boot `v3.5.0`           |
| Language          | Java `17`                      |
| Database          | MySQL with Spring Data JPA     |
| API Documentation | SpringDoc OpenAPI (Swagger UI) |
| Security          | Spring Security + JWT          |
| Caching           | Spring Boot Cache              |
| Dependency Mgmt   | Maven                          |
| Email             | Spring Boot Mail               |
| Object Mapping    | ModelMapper                    |
| Dev Tools         | Lombok, Devtools               |

---

## 📁 Project Structure
<pre><code>
  src/
├── main/
│ ├── java/com/product/
│ │ ├── config/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── endpoints/
│ │ ├── enums/
│ │ ├── exception/
│ │ ├── filter/
│ │ ├── genricResponse/
│ │ ├── model/
│ │ ├── page/response/
│ │ ├── repository/
│ │ ├── routes/
│ │ ├── security/
│ │ ├── service/
│ │ ├── service/impl/
│ │ ├── util/
│ │ ├── validation/
│ │ └── EcommerceApplication.java
│ └── resources/
│ ├── application.properties
│ └── static/
└── test/
</code></pre>

---

## 📘 API Documentation

The complete API is documented using Swagger UI:

- 🔗 Local: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- 🌐 Production: [https://your-domain.com/swagger-ui/index.html](https://your-domain.com/swagger-ui/index.html)

---

## 👤 Author

**Sahil Kumar Mandal**

- 💼 [LinkedIn](https://www.linkedin.com/in/sahil-mandal-588380245/)
- 🐙 [GitHub](https://github.com/SahilWeb-lab)
- 📧 Email: [mandalsahil253@gmail.com](mailto:mandalsahil253@gmail.com)

---

## 🛠️ Installation & Run

```bash
# Clone the repository
git clone https://github.com/SahilWeb-lab/MyStore.git
cd MyStore

# Build and run
./mvnw spring-boot:run

services:
  mainapp:
    build:
      context: .
    container_name: ecomapp
    depends_on:
      - mysql_db
    environment:
      SPRING_DATASOURCE_URL: "jdbc:mysql://mysql_db:3306/ecom_db?allowPublicKeyRetrieval=true&useSSL=false"
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: ""
    ports:
      - "8080:8080"
    networks:
      - ecomapp-network
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8080 || exit 1"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s
    restart: always

  mysql_db:
    image: mysql:latest
    container_name: mysql
    environment:
      MYSQL_ALLOW_EMPTY_PASSWORD: "yes"
      MYSQL_DATABASE: ecom_db
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql
    networks:
      - ecomapp-network
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot"]
      interval: 10s
      timeout: 5s
      retries: 5
      start_period: 30s
    restart: always

volumes:
  mysql-data:

networks:
  ecomapp-network:
    driver: bridge

