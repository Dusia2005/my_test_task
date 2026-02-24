Тестовое задание: сервис для работы с объектами недвижимости (отели).

Проект соответствует следующим обязательным требованиям:
- **Запуск из консоли:** `mvn spring-boot:run`
- **Порт:** `8092`
- **Базовый URL:** все методы имеют префикс `/property-view`
- **БД:** H2 (in-memory)
- **Миграции:** Liquibase

## Стек технологий

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Liquibase
- H2 Database
- Maven
- (Опционально) Swagger/OpenAPI

## Запуск приложения

### Предварительные требования
- Установленный JDK 17 или выше
- Установленный Maven

### Запуск

# Клонировать репозиторий
git clone <url-репозитория>
cd property-view-service

# Запустить приложение
mvn spring-boot:run
После запуска приложение будет доступно по адресу: http://localhost:8092

Примеры запросов
text
GET    /property-view/hotels      # Получить краткий список отелей
GET    /property-view/hotels/{id} # Получить подробную информацию конкретного отеля
GET    /property-view/search      # Поиск получение списка всех отелей с их краткой информацией по следующим параметрам: name, brand, city, country, amenities.
GET    /property-view/histogram/{param}     # Получение колличества отелей сгруппированных по каждому значению указанного параметра. Параметр: brand, city, country, amenities.
POST   /property-view/hotels      # Создать отель
POST   /property-view/{id}/amenities      # Добавление списка amenities к отелю

Переключение на другую БД (опционально)
Проект спроектирован с возможностью легкой смены базы данных.
Для переключения, например, на PostgreSQL:

Добавьте зависимость в pom.xml:

xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
Измените application.yml:

yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/propertydb
    username: your_username
    password: your_password
  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
