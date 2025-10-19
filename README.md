# Kotlin Address Book – BEO Coding Challenge

### Overview

This is a Kotlin project I built as part of the BEO coding challenge.
It’s a simple address book application built with **Spring Boot**, following **DDD** and **Hexagonal Architecture** principles.

My goal while building this was to keep the code clean, modular, and close to how I would design something in a real-world system — with a clear domain layer, a thin service layer, and a replaceable persistence layer (PostgreSQL ready).

---

### What it does

The application allows to manage contacts through REST endpoints.
Each contact can have multiple addresses and phone numbers.

**Supported operations:**

* Add a new contact (must have at least one address or phone)
* Edit a contact
* Delete a contact (and related details)
* List all contacts
* View a single contact
* Search contacts by last name (exact or prefix match)

The validation rules are built into the domain model and service layer, so invalid data doesn’t sneak in.

---

### How I structured the project

I used a **Hexagonal (Ports and Adapters)** style layout.
The domain doesn’t depend on Spring or any database — only on plain Kotlin.

```
com.beo.addressbook
│
├── domain/
│   ├── model/
│   │   ├── Contact.kt
│   │   ├── Address.kt
│   │   ├── PhoneNumber.kt
│   │   ├── AddressType.kt
│   │   └── PhoneType.kt
│   │
│   └── repository/
│       └── ContactRepository.kt        
│
├── action/
│   └── ContactService.kt               
│
├── infra/
│   ├── api/
│   │   └── ContactController.kt        
│   │
│   ├── database/
│   │   ├── ContactEntity.kt
│   │   ├── AddressEntity.kt
│   │   │── PhoneNumberEntity.kt
│   │   ├── ContactMapper.kt
│   │   ├── ContactJpaRepository.kt
│   │   │── ContactRepositoryImpl.kt
│   │
│   └── exception/
│       └── GlobalExceptionHandler.kt
│
├── ContactServiceApplication.kt
│
└── resources/
    ├── application.yml
    

```

This separation made it easy to swap between H2 and PostgreSQL without touching the core code.

---

### Tech Stack

* **Kotlin (JDK 17+)**
* **Spring Boot**
* **Spring Data JPA**
* **Postgres Database** 
* **Gradle (Kotlin DSL)**
* **JUnit 5** for testing

---

### Running the app locally

#### 1. Clone the project

```bash
git clone https://github.com/madhankumar2592/contact-service.git
cd beo-addressbook
```

#### 2. Build and run

```bash
./gradlew bootRun
```

The app runs on **[http://localhost:8080](http://localhost:8080)**

---

### REST API Endpoints

| Method | Endpoint                          | Description                |
| ------ | --------------------------------- | -------------------------- |
| POST   | `/contacts`                       | Add a new contact          |
| GET    | `/contacts`                       | List all contacts          |
| GET    | `/contacts/{id}`                  | Get a specific contact     |
| PUT    | `/contacts/{id}`                  | Update an existing contact |
| DELETE | `/contacts/{id}`                  | Delete a contact           |
| GET    | `/contacts/search?lastName=Kumar` | Search by last name        |

---

### Sample Request (Add Contact)

```json
{
  "firstName": "Madhan",
  "lastName": "Kumar",
  "addresses": [
    {
      "street": "Gandhipuram Main Rd",
      "houseNumber": "102A",
      "city": "Coimbatore",
      "type": "PRIVATE"
    }
  ],
  "phoneNumbers": [
    {
      "number": "9876543210",
      "type": "MOBILE_PRIVATE"
    }
  ]
}
```

---

### Design thoughts

* I kept the **domain layer independent** — no Spring or JPA annotations there.
* Validation is handled where it makes sense:

  * Basic checks (like first/last name) inside the domain constructor.
  * Contextual checks (like at least one address or phone) inside the service.
* Repository pattern allows me to easily switch between database types later.
* Used Kotlin `data class` + `copy()` to simplify updates.

---

### Final thoughts

This challenge was fun to build — it’s small, but it captures real-world layering and design ideas I use in larger systems.
If I had more time, I’d add flyway scripts, but the current version is complete and functional.

---

**Developed by:**
**Madhan Kumar**
Clean code • Practical architecture • Kotlin 
