# Employee Management System

Mini project Spring Boot theo 10 lab trong lộ trình: IoC/custom bean, REST CRUD, JPA, validation và lỗi chuẩn Problem Details, Thymeleaf, logging/profiles, Actuator, caching 60 giây, scheduled task, Spring Security và reporting.

## Chạy nhanh

```bash
mvn clean test
mvn spring-boot:run
```

Mặc định project dùng profile `dev` với H2 in-memory. Truy cập:

- Web UI: `http://localhost:8080/employees/list`
- Hello API: `GET /api/hello`
- REST CRUD: `/api/employees`
- Báo cáo: `/api/reports/employee-count`, `/api/reports/by-department`
- Actuator: `/actuator/health`, `/actuator/metrics`
- H2 console: `/h2-console` (`jdbc:h2:mem:employees`, user `sa`, mật khẩu trống)

Tài khoản mẫu cho môi trường học tập:

- `user` / `User@123`: xem danh sách và báo cáo.
- `admin` / `Admin@123`: toàn quyền CRUD.

Đăng ký user mới qua `POST /api/auth/register`. Đăng nhập API bằng HTTP Basic; đăng nhập giao diện bằng form mặc định của Spring Security.

## Production profile

```bash
java -jar target/employee-management-1.0.0.jar --spring.profiles.active=prod
```

Thiết lập `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. `DB_URL` có thể là JDBC URL của MySQL hoặc PostgreSQL; schema production phải được migration trước vì `ddl-auto=validate`.

## Ví dụ tạo nhân viên

```bash
curl -u admin:Admin@123 -H "Content-Type: application/json" \
  -d '{"name":"Le Minh Khoa","email":"khoa@example.com","departmentId":1}' \
  http://localhost:8080/api/employees
```
