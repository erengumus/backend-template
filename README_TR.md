
# Spring Boot Backend Template

# Açıklama
Bu proje, bir kullanıcı yönetim sisteminin temel işlevlerini sağlayan bir uygulamadır. Spring Boot 3, JWT tabanlı kimlik doğrulama ve yetkilendirme, veri yönetimi ve rol tabanlı erişim gibi önemli özellikler içerir.

## Kullanılan Teknolojiler

- **Java**: Backend için birincil programlama dili.
- **Spring Boot 3**: RESTful API’ler geliştirmek için framework.
- **Spring Security**: Kimlik doğrulama ve yetkilendirme.
- **MapStruct**: DTO ve Entity dönüşümleri.
- **Liquibase**: Veritabanı şeması yönetimi ve versiyon kontrolü.
- **PostgreSQL**: Veritabanı yönetim sistemi.
- **Spring Data JPA**: Veritabanı işlemleri için JPA.
- **Redis**: Önbellek yönetimi.
- **JWT (JSON Web Tokens)**: Token tabanlı kullanıcı oturumu güvenliği.
- **Slf4j & Logback**: Loglama framework’ü.
- **Maven**: Proje yönetimi ve bağımlılık konfigürasyonu.

# Özellikler

- **JWT Tabanlı Kimlik Doğrulama**  
  Kullanıcı girişleri için token oluşturma ve doğrulama.

- **Dinamik Rol Yönetimi**  
  Rollerin (örn. `ADMIN`, `USER`) bir tabloda saklanması ve kullanıcılara dinamik olarak atanması.

- **Liquibase ile Veritabanı Yönetimi**  
  Veritabanı şeması Liquibase kullanılarak SQL formatında yönetilir.

- **Çoklu Dil Desteği**  
  Hata ve bilgilendirme mesajları `messages.properties` dosyalarında tanımlanmıştır.

- **Oturum Yönetimi**  
  Kullanıcı bilgileri oturumdan alınabilir.

- **Loglama**  
  Tüm işlemler `DEBUG` ve `INFO` seviyelerinde loglanır.

# API Uç Noktaları

## Kimlik Doğrulama

- **POST** `/api/auth/register`  
  Yeni bir kullanıcı kaydı.

- **POST** `/api/auth/login`  
  Kullanıcı girişi.

## Kullanıcı İşlemleri

- **GET** `/api/auth/user`  
  Kullanıcı bilgilerini getir.

- **GET** `/api/auth/welcome`  
  Kullanıcıya özel bir hoş geldin mesajı.

- **GET** `/api/auth/checkLocale`  
  Locale kontrolü yap.

# Kurulum

## 1. **Bağımlılıkları Yükleyin:**
Başlamak için gerekli bağımlılıkları yükleyin.

   ```bash
   mvn clean install
   ```

## 2. **Liquibase ile Veritabanı Kurulumu:**
Veritabanı şemanızı Liquibase kullanarak oluşturun.

   ```bash
   mvn liquibase:update
   ```

## 3. **Uygulamayı Başlatın:**
Son olarak, uygulamayı çalıştırın.

   ```bash
   mvn spring-boot:run
   ```

# Project Structure

```plaintext
src/main
├── java/com/template/backendtemplate
│   └── core
│      ├── auth         # Kimlik doğrulama ve rol yönetimi
│      ├── config       # Spring Security ve JWT yapılandırmaları
│      ├── entity       # Temel Entity
│      ├── exception    # Global Exception Handler
│      ├── filter       # JWT filtreleri
│      ├── listener     # Auditable Entity Listener
│      ├── messages     # Çoklu dil desteği
│      ├── model        # JWT Modelleri
│      ├── service      # Oturum ve mesaj servisleri
│      └── utils        # Yardımcı sınıflar (ör. JwtUtil)
│   
│
├── resources
│   ├── db/changelog        # Liquibase değişiklik dosyaları
│   ├── messages            # Çoklu dil dosyaları
│   ├── application.yml     # Yapılandırma
│   └── docker-compose.yml  # Yerel PostgreSQL ve Redis yapılandırması
```

# Kurulum ve Çalıştırma

## Gereksinimler:
- JDK 17 veya üzeri
- Maven
- PostgreSQL veritabanı
- Redis (isteğe bağlı, önbellekleme için)

## Kurulum Adımları:
1. Bu depoyu klonlayın.
   ```bash
   git clone https://github.com/erengumus/backend-template.git

2. Proje dizinine gidin.
   ```bash
   cd backend-template
   ```

3. Bağımlılıkları yükleyin ve uygulamayı çalıştırın:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

# License

Bu proje MIT Lisansı ile lisanslanmıştır - detaylar için LICENSE dosyasına bakın.