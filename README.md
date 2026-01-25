# 🏨 Hotel Management System - RESTful API

Dự án xây dựng hệ thống Backend cho Quản lý khách sạn (Hotel Management System) sử dụng hệ sinh thái Spring Framework. Đây là đồ án thực hành trong khóa học **Java Spring Developer Launchpad**.

---

## 🛠️ Công nghệ sử dụng (Technology Stack)
Dự án tuân thủ các yêu cầu kỹ thuật tại Slide 5 của Project Assignment:
- **Ngôn ngữ:** Java 17+ (LTS)
- **Framework:** Spring Boot 3.x
- **Lưu trữ dữ liệu:** MySQL 8.x
- **Truy vấn dữ liệu:** Spring Data JPA (Hibernate 6.x)
- **Tài liệu API:** Swagger UI (OpenAPI 3.0 via SpringDoc)
- **Quản lý thư viện:** Apache Maven 3.x
- **Kiến trúc:** Layered Architecture (Controller - Service - Repository - Entity)

---

## 📂 Các Domain chính trong hệ thống
Hệ thống tập trung vào các nghiệp vụ cốt lõi (Core Domains):
1. **Guest Management:** Đăng ký, quản lý hồ sơ và chương trình khách hàng thân thiết.
2. **Room Management:** Quản lý kho phòng, loại phòng và tình trạng phòng.
3. **Reservation Management:** Quy trình đặt phòng, check-in, check-out và hủy phòng.
4. **Service & Billing:** Quản lý dịch vụ bổ sung và tự động tính hóa đơn, thuế, phí.

---

## 🚀 Hướng dẫn cài đặt và khởi chạy (Getting Started)

### 1. Yêu cầu hệ thống
- Máy tính đã cài sẵn **JDK 17**.
- Đã cài đặt **MySQL Server** (version 8.x).
- IDE (Khuyên dùng **Apache NetBeans** hoặc **IntelliJ IDEA**).

### 2. Cấu hình cơ sở dữ liệu
1. Mở MySQL Workbench hoặc Command Line và tạo Database:
   ```sql
   CREATE DATABASE hotel_db;
   ```

1. Tại thư mục gốc dự án, tìm file src/main/resources/application.properties.example.
2. Tạo một bản sao và đổi tên thành application.properties.
3. Cập nhật thông tin tài khoản MySQL của bạn vào file mới tạo:
```Properties
spring.datasource.username=root
spring.datasource.password=dien_mat_khau_cua_ban
```
### 3. Chạy ứng dụng
Trên NetBeans: Chuột phải vào Project -> Run.
Trên Terminal:

```Bash
mvn spring-boot:run
```

## 📖 Tài liệu API (Swagger UI)
Hệ thống tự động tạo tài liệu API dựa trên Swagger. Sau khi server khởi động (mặc định port 8080), bạn có thể truy cập tại:
👉 URL: http://localhost:8080/swagger-ui/index.html

## ⚖️ Các Business Rules quan trọng (Critical Rules)
Dự án cài đặt các quy tắc nghiệp vụ theo yêu cầu tại Slide 9 & 11:
- BR-003: Không cho phép đặt trùng phòng (Double-booking) trong cùng một khoảng thời gian.
- BR-101: Khách hàng phải từ 18 tuổi trở lên mới được đặt phòng.
- BR-301: Hóa đơn tự động tính 10% thuế VAT và 5% phí dịch vụ.
- BR-008: Mã xác nhận đặt phòng theo định dạng: HTLYYYYMMDD-XXXXX.

## 👥 Nhóm thực hiện
- Mai Phương Anh: Database Developer & Infrastructure (Entity, Repository, JPA Mapping).
- Tào Thanh Hà: Backend Developer & Business Logic (Service Layer, Controller, Exception Handling).