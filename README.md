# GrabFreshFood 🛒

GrabFreshFood is a mock e-commerce supermarket web application that allows users to browse groceries, manage their shopping cart, and complete checkout — offering a smooth and responsive user experience.


![149af217-3b56-43b5-aeff-e1b6a62d4470](https://github.com/user-attachments/assets/98175a98-a75f-4ae4-a507-ad59654df815)

## 🚀 Features

- 🛍️ Product catalogue with dynamic display
- 🛒 Fully functional shopping cart
- 🧾 Real-time subtotal and total calculation
- ✅ Account creation and login functionality
- 🔒 Secure session and cart management
- ⭐ Product detail pages with reviews and ratings
- 📦 Checkout summary with service fee breakdown

---

## 🧰 Tech Stack

### Frontend
- **React.js** – For building responsive, dynamic UIs
- **Bootstrap** – For styling and layout components

### Backend
- **Java Spring Boot (MVC)** – For RESTful API design and server-side logic
- **JPA (Java Persistence API)** – For ORM and database interaction
- **Java Thymeleaf** – For server-rendered pages like login and cart

### Database
- **MySQL** – For structured storage of user, product, and order data

---

## 🖼️ Sample Pages

| Homepage | Product Details | Shopping Cart | Login Page |
|----------|------------------|----------------|-------------|
| ![Home](./screenshots/home.png) | ![Product](./screenshots/product.png) | ![Cart](./screenshots/cart.png) | ![Login](./screenshots/login.png) |

> ℹ️ *Replace the image paths above with actual screenshots from your `/screenshots/` folder.*

---

## 🏁 Getting Started

### Prerequisites

- Node.js & npm
- Java 17+
- MySQL server
- Maven

### Backend Setup

```bash
# Clone the repository
git clone https://github.com/yourusername/grabfreshfood.git
cd grabfreshfood/backend

# Set up database credentials in application.properties
# Run the Spring Boot application
mvn spring-boot:run
