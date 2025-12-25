### Технологии
- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **Retrofit 2**
- **Coroutines**
- **Hilt (DI)**
- **MVVM + Clean Architecture**

### Экраны приложения
1. **Меню**
    - список доступных напитков
    - загрузка с backend (`GET /coffees`)

2. **Оформление заказа**
    - ввод имени, адреса, количества
    - отправка заказа (`POST /orders`)

3. **История заказов**
    - загрузка заказов из БД
    - кнопка обновления (`GET /orders`)

4. **О приложении**
    - описание проекта
    - статическая информация

### Архитектура (Android)
presentation/
├─ menu
├─ order
├─ history
└─ about

domain/
├─ model
├─ repository
└─ usecase

data/
├─ api
├─ dto
├─ mapper
└─ repository


---

## 🧠 Backend (Spring Boot)

### Технологии
- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **PostgreSQL**
- **Hibernate**
- **Gradle**

### REST API

| Метод | URL        | Описание |
|-----:|------------|----------|
| GET  | `/coffees` | Получить меню |
| POST | `/orders`  | Создать заказ |
| GET  | `/orders`  | Получить историю заказов |

### Пример запроса создания заказа
```json
POST /orders
{
  "customerName": "Ivan",
  "address": "Moscow",
  "coffeeId": 1,
  "quantity": 2
}

{
  "orderId": 5,
  "status": "CREATED"
}
