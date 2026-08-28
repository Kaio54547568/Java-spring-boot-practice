# Spring Core Practice

Bài thực hành Maven minh họa IoC/Dependency Injection, custom bean, AOP và truy cập dữ liệu với `JdbcTemplate`.

## Chạy ứng dụng

```bash
mvn clean test
mvn exec:java
```

Ứng dụng dùng H2 in-memory, tự tạo dữ liệu mẫu từ `schema.sql` và `data.sql`, không cần cài database bên ngoài.
