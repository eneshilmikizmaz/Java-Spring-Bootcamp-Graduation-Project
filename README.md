# Patika.Dev - Logo Software Java Spring Backend Bootcamp Graduation Project

**_Bu bitirme projesinde, feign client kullanarak senkron mikro servis işlemlerini, rabbitMq ile asenkron iletişim ve
kuyruk yapısını kullandım aynı zamanda bu yapıyı kullanırken polimorfik bir yapı kurmaya çalıştım.
Docker üzerinde kaldırmış olduğum postgreSql ile RDBMS yi deneyimledim. Hibernate kullanarak DB ye CRUD işlemlerini
yazdım. Bir service common yapısı kurguladım. Aynı zamanda Euraka server kullanarak service registry işlemlerini
gerçekleştirdim. Gateway service i kullanarakta gelen istekleri yönlendirdim. Bu proje de deneyimlediğim en güzel
tecrübe ise mikro servisler arası senkron iletişimlerin kurgulanmasıydı._**

`Linkedin :`
[https://www.linkedin.com/in/eneshilmikizmaz/](https://www.linkedin.com/in/eneshilmikizmaz/)

## Konu Ve Analiz:

Online uçak ve otobüs bilet satış uygulaması için gerekli API’lerin yazılması. Ayrıca sisteme yolculuk seferlerinin
sisteme girilmesi için de gerekli API’ler yazılmalıdır.

## User Service

- Kullanıcılar sisteme kayıt ve login olabilmelidir.
- Kullanıcı kayıt işleminden sonra mail gönderilmelidir.
- Kullanıcı şifresi istediğiniz bir hashing algoritmasıyla database kaydedilmelidir.

---

- Kullanıcılar bireysel ve kurumsal olabilir.

| ColumnName | Type | IsNull | Default |
| --- | --- | --- | --- |
| id | int |  |  |
| username | VARCHAR(24) | * |  |
| password | VARCHAR(24) |  |  |
| email | VARCHAR(50) |  |  |
| phone | VARCHAR(24) | * |  |
| role | int(4) | * |  |
| type | int(4) |  |  |
| company_id | int |  |  |

| ColumnName | Type | IsNull | Default |
| --- | --- | --- | --- |
| id | int |  |  |
| company_name | VARCHAR(24) | * |  |
| company_type | VARCHAR(24) |  |  |

## Payment Service

- Bireysel kullanıcı aynı sefer için en fazla 5 bilet alabilir.
- Bireysel kullanıcı tek bir siparişte en fazla 2 erkek yolcu için bilet alabilir.
- Kurumsal kullanıcı aynı sefer için en fazla 20 bilet alabilir.
- Admin toplam bilet satışını, bu satıştan elde edilen toplam ücreti görebilir.
- Satın alma işlemi başarılı ise işlem tamamlanmalı ve asenkron olarak bilet detayları kullanıcının telefona numarasına
  mesaj gönderilmeli.

---

- Postgresql kullanılacak
- Ödeme şekli sadece Kredi kartı ve Havale / EFT olabilir.
- Ödeme Servisi işlemleri Senkron olmalıdır.

| ColumnName | Type | IsNull | Default |
| --- | --- | --- | --- |
| id | VARCHAR(20) |  |  |
| ticket_id | VARCHAR(24) | * |  |
| amount_paid | VARCHAR(24) |  |  |
| payment_date | Date |  |  |
| user_id | int |  |  |
| numerofSeat | int |  |  |

## Ticket Service

- Kullancılar aldığı biletleri görebilmelidir.

---

| ColumnName | Type | IsNull | Default |
| --- | --- | --- | --- |
| id | VARCHAR(20) |  |  |
| schedule_id | VARCHAR(24) | * |  |
| user_id | VARCHAR(24) |  |  |
| number_of_seats | VARCHAR(50) |  |  |
| fare_amount | VARCHAR(24) | * |  |
| date_of_booking | datetime |  |  |
| booking_status | bool |  |  |

## Notification Service

- Mesaj ve mail gönderme işlemleri için sadece Database kayıt etme
  işlemi yapması yeterlidir. Fakat bu işlemler tek bir Servis(uygulama) üzerinden ve polimorfik davranış ile
  yapılmalıdır.

---

- Mesaj ve Mail gönderim işlemleri Asenkron olmalıdır.
- Rabbitmq kullanılacak

## Admin Service

- Admin kullanıcı yeni sefer ekleyebilir, iptal edebilir.
- Kullanıcılar şehir bilgisi, taşıt türü(uçak & otobüs) veya tarih bilgisi ile tüm seferleri arayabilmelidir.

---

- Uçak yolcu kapasitesi: 189
- Otobüs yolcu kapasitesi: 45
- Mysql kullanılacak hızlı ve basit

| ColumnName | Type | IsNull | Default |
| --- | --- | --- | --- |
| Id | VARCHAR(20) |  |  |
| starting_point | VARCHAR(30) | * |  |
| destination | VARCHAR(30) | * |  |
| schedule_date | date | * |  |
| departure_time | time | * |  |
| estimated_arrival_time | time |  |  |
| fare_amount | float |  |  |
| capacity | int |  |  |
| type | int |  |  |