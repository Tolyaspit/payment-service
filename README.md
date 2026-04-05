# Payment Service API

REST API сервис для управления платежами. Позволяет создавать, подтверждать, отменять платежи и получать информацию о них.

---

## Quick Start

```bash
git clone https://github.com/TolegenKH/payment-service.git
cd payment-service
mvn spring-boot:run

После запуска API будет доступен по адресу:
http://localhost:8080

📋 Функциональность
Создание платежа (статус PENDING по умолчанию)
Получение информации о платеже по ID
Подтверждение платежа (CONFIRMED)
Отмена платежа (CANCELED)
Получение списка платежей клиента
Валидация входных данных
Обработка ошибок (400, 404)

🛠 Технологии
Java 21
Spring Boot 4.0.5
Spring Data JPA
PostgreSQL
MapStruct
Jakarta Validation
Lombok
springdoc-openapi (Swagger)

🏗 Архитектура
Приложение построено по слоям:
Controller — обработка HTTP запросов
Service — бизнес-логика
Repository — доступ к данным
DTO + Mapper — разделение API и модели данных

⚙️ Настройка базы данных
Создайте базу данных:
CREATE DATABASE payment_db;

Настройте подключение в src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/payment_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

📚 API Документация
Swagger UI
Доступен по адресу:
http://localhost:8080/swagger-ui/index.html

🔗 Эндпоинты
Метод	URL	Описание
POST	/api/payments	Создание платежа
GET	/api/payments/{paymentId}	Получение платежа
POST	/api/payments/{paymentId}/confirm	Подтверждение
POST	/api/payments/{paymentId}/cancel	Отмена
GET	/api/clients/{clientId}/payments	Список платежей клиента

📦 Примеры API
1. Создание платежа
POST /api/payments
{
  "amount": 1000,
  "currency": "KZT",
  "description": "Оплата заказа №123",
  "clientId": "12345"
}
Ответ:
{
  "paymentId": 1,
  "status": "PENDING"
}
2. Получение платежа
GET /api/payments/1
{
  "paymentId": 1,
  "amount": 1000,
  "currency": "KZT",
  "description": "Оплата заказа №123",
  "clientId": "12345",
  "status": "PENDING"
}
3. Подтверждение платежа
POST /api/payments/1/confirm
{
  "paymentId": 1,
  "status": "CONFIRMED"
}
4. Отмена платежа
POST /api/payments/1/cancel
{
  "paymentId": 1,
  "status": "CANCELED"
}
5. Список платежей клиента
GET /api/clients/12345/payments
[
  {
    "paymentId": 1,
    "amount": 1000,
    "currency": "KZT",
    "status": "CONFIRMED"
  },
  {
    "paymentId": 2,
    "amount": 500,
    "currency": "USD",
    "status": "PENDING"
  }
]

⚠️ Обработка ошибок
404 — Платеж не найден
{
  "status": 404,
  "error": "Not Found",
  "message": "Payment not found with id: 999"
}

400 — Некорректная операция
{
  "status": 400,
  "error": "Bad Request",
  "message": "Cannot confirm payment with status: CONFIRMED"
}

400 — Ошибка валидации
{
  "amount": "Amount must be positive",
  "currency": "Currency must be one of: KZT, USD, EUR, RUB, CNY",
  "clientId": "Client ID is required"
}

✅ Валидация данных

amount	положительное число
currency	KZT, USD, EUR, RUB, CNY
description	до 255 символов (опционально)
clientId	обязательное

📁 Структура проекта
payment-service/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
├── mapper/
├── exception/
└── resources/

 Postman
Postman коллекция находится в папке /postman.

 Автор
GitHub: https://github.com/TolegenKH
