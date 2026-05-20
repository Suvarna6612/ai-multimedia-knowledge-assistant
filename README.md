# AI Multimedia Knowledge Assistant

An intelligent full-stack application that allows users to upload PDF, audio, and video files, automatically extracts and summarizes their content, and answers natural language questions using AI-powered semantic search. For audio and video files, the application also provides relevant timestamps so users can jump directly to the most relevant moment in the media.

---

## 🚀 Features

- Upload PDF, MP3, WAV, MP4, and other multimedia files
- Extract text from PDF documents
- Transcribe audio and video files
- Generate AI-powered summaries
- Ask questions about uploaded content
- Retrieve relevant timestamps from transcripts
- Play media from exact timestamps
- Store metadata and summaries in PostgreSQL
- RESTful API with Spring Boot
- Modern React frontend
- Dockerized backend and database

---

## 🛠️ Tech Stack

### Backend
- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Ollama

### Frontend
- React
- Vite
- Axios
- CSS

### DevOps
- Docker
- Docker Compose
- Git
- GitHub

---

## 🏗️ Architecture

React Frontend (Vite)
        ↓
Spring Boot REST API
        ↓
Business Services
        ↓
PostgreSQL Database
        ↓
Ollama Local AI Model

---

## 📂 Project Structure

ai-multimedia-knowledge-assistant/
│
├── backend/
│   └── ai-multimedia-knowledge-assistant/
│       ├── src/
│       ├── Dockerfile
│       ├── docker-compose.yml
│       └── pom.xml
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
└── README.md

---

## ⚙️ Setup Instructions

### 1. Clone Repository

git clone https://github.com/Suvarna6612/ai-multimedia-knowledge-assistant.git
cd ai-multimedia-knowledge-assistant

### 2. Start Backend and PostgreSQL

cd backend/ai-multimedia-knowledge-assistant
docker compose up --build

### 3. Start Frontend

Open a new terminal:

cd frontend
npm install
npm run dev

### 4. Open Application

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080
- Health Check: http://localhost:8080/actuator/health

---

## 📡 API Endpoints

### Upload File
POST /api/files/upload

### Ask Question
POST /api/chat/ask

### Health Check
GET /actuator/health

---

## 🐳 Docker Commands

### Start Containers
docker compose up --build

### Stop Containers
docker compose down

### View Running Containers
docker ps

---

## 🗄️ Database Tables

- uploaded_files
- transcript_segments

---

## 🎯 Resume Highlights

- Developed a full-stack AI application that processes PDF, audio, and video files and answers context-based questions.
- Implemented timestamp-based playback for multimedia content using transcript segment matching.
- Integrated PostgreSQL with Spring Data JPA for persistent storage.
- Containerized the application using Docker and Docker Compose for production-style deployment.

---

## 🔮 Future Enhancements

- User authentication and authorization
- Cloud deployment
- Semantic vector search
- Multi-language transcription
- Analytics dashboard

---

## 👨‍💻 Author

Suvarna

GitHub: https://github.com/Suvarna6612

---

## ⭐ Repository

https://github.com/Suvarna6612/ai-multimedia-knowledge-assistant
