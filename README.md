# 🛒 E-Commerce REST API (Spring Boot)

A real-world, production-ready **eCommerce backend** built using **Spring Boot**, designed to power web and mobile frontends.

---

## 📦 Features

### 🧑 User Module
- User registration & login (JWT-based auth)
- Role-based access (USER, ADMIN)
- Profile management
- Email Verification
- Password Reset

### 🛍️ Product Module
- Product CRUD (Admin)
- Product listing & filtering
- Product categories
- Product images

### 🏷️ Category Module
- Category CRUD (Admin)
- Category listing & filtering

### 🛍️ Brand Module
- Brand CRUD (Admin)
- Brand listing & filtering

### 🛒 Cart & Wishlist
- Add/remove products to cart
- Modify quantities
- Wishlist management

### 💰 Checkout & Orders
- Address management
- Order placement & history
- Order tracking status
- Cancel Order

### 🧑‍💻 Admin Panel
- Manage products, users, categories, brands
- View all orders
- Activate/deactivate users
- Dashboard stats (sales, users, etc.)
- Change order status

### 🔐 Security
- JWT Authentication
- Spring Security with role-based endpoints
- CORS enabled for frontend integration

---

## 🚀 Tech Stack


| Layer             | Technology                                 |
|-------------------|---------------------------------------------|
| Framework         | Spring Boot 3.5.0                           |
| Language          | Java 17                                     |
| Database          | MySQL (via JPA/Hibernate)                   |
| API Docs          | SpringDoc OpenAPI (Swagger UI)              |
| Security          | Spring Security + JWT                       |
| Caching           | Spring Boot Cache                           |
| Dependency Mgmt   | Maven                                       |
| Email             | Spring Boot Mail                            |
| Object Mapping    | ModelMapper                                 |
| Dev Tools         | Lombok, Devtools                            |

---

## 📁 Project Structure

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
