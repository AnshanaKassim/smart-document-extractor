# 🔍 Smart Document Extractor API

An AI-powered REST API built with Java 21 and Spring Boot that extracts structured data from uploaded PDF documents using OpenAI GPT.

## 🌐 Live Demo
Access the live application deployed on AWS EC2:
http://44.205.15.123:8080

## 💡 Use Case
Automates manual data entry for financial documents such as CVs, invoices, payslips, and contracts — relevant to KYC, onboarding, and compliance workflows in FinTech.

## 🛠 Tech Stack
- Java 21
- Spring Boot 4
- OpenAI GPT API (gpt-3.5-turbo)
- MySQL / MariaDB
- Spring Data JPA / Hibernate
- PDFBox 3.0
- Docker
- AWS EC2 (Europe - London)
- REST APIs

## ✨ Features
- Upload PDF documents via REST API or web UI
- AI extracts structured JSON (name, date, amounts, document type, summary)
- Saves results to MySQL with audit timestamp
- Search extractions by document type
- Interactive web UI with drag and drop upload
- Dockerised for consistent deployment

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/documents/extract | Upload PDF and extract data |
| GET | /api/documents | Get all extractions |
| GET | /api/documents/search?type= | Search by document type |
| GET | /api/documents/test | Health check |

## 🚀 How to Run Locally

### Prerequisites
- Java 21
- MySQL
- OpenAI API key

### Steps

1. Clone the repository
```bash
git clone https://github.com/AnshanaKassim/smart-document-extractor.git
cd smart-document-extractor
```

2. Set up database
```sql
CREATE DATABASE extractor_db;
```

3. Configure properties
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Edit `application.properties` with your MySQL password and OpenAI API key.

4. Run the application
```bash
./mvnw spring-boot:run
```

5. Open in browser
```
http://localhost:8080
```
## 🐳 Run with Docker

```bash
docker pull anshanakassim/smart-extractor
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your-db-host:3306/extractor_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=yourpassword \
  -e OPENAI_API_KEY=your-openai-key \
  anshanakassim/smart-extractor
```

## ☁️ Deployment
- Deployed on AWS EC2 (t3.micro, Europe London eu-west-2)
- MariaDB running on the same EC2 instance
- Docker container running in detached mode
- Publicly accessible via port 8080

## 👩‍💻 Author
Anshana Kassim — Software Developer.  
[GitHub](https://github.com/AnshanaKassim) | [LinkedIn](https://www.linkedin.com/in/anshanakassim/)