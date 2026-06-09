# AI Multimedia Knowledge Assistant 🚀

AI Multimedia Knowledge Assistant is a full-stack AI-powered platform designed to process, analyze, and interact with multimedia content including PDFs, audio files, and videos. The application enables users to upload multimedia data, generate AI-powered summaries, perform contextual question answering, transcribe speech content, and navigate directly to relevant timestamps within media files.

The system combines modern full-stack engineering with AI-driven multimedia processing using Spring Boot, React, PostgreSQL, Spring AI, Whisper AI, Ollama Local LLMs, Groq Cloud Inference, and FFmpeg.

---

# 🌐 Live Deployment

## Frontend Application

```text
https://ai-multimedia-knowledge-assistant-1.onrender.com
```

## Backend API

```text
https://ai-multimedia-knowledge-assistant.onrender.com
```

---

# ✨ Core Features

## 📄 Intelligent PDF Processing

* Extracts textual content from uploaded PDF documents
* Generates AI-powered contextual summaries
* Supports semantic question answering over document content

## 🎧 Speech-to-Text Audio Transcription

* Converts uploaded audio files into searchable transcripts
* Supports MP3, WAV, MPEG, AAC, and M4A formats
* Powered by Whisper AI speech recognition models

## 🎥 AI-Powered Video Understanding

* Processes uploaded multimedia video content
* Generates transcript-aware AI responses
* Enables timestamp-based multimedia navigation

## 🤖 Contextual AI Question Answering

* Supports natural language interaction with uploaded multimedia
* Generates context-aware semantic responses
* Uses local and cloud-based LLM integration

## ⏱ Timestamp-Based Playback Navigation

* Detects relevant timestamps from generated AI responses
* Allows direct navigation to important multimedia segments

## 🌐 Modern Full-Stack Architecture

* RESTful backend services using Spring Boot
* Responsive frontend built with React and Vite
* Persistent multimedia storage using PostgreSQL
* Scalable deployment-ready architecture

---

# 🤖 Hybrid AI Architecture

The application uses a hybrid AI processing strategy optimized for both local development and public cloud deployment.

## 🔹 Local Development Environment

For local development and advanced multimedia experimentation, the system integrates:

* Ollama Local LLMs
* Whisper AI Speech-to-Text
* FFmpeg Multimedia Processing

This environment supports:

* PDF processing
* Audio transcription
* Video understanding
* Timestamp-aware multimedia navigation
* Fully local AI inference

### Local Models Used

* Llama 3.2
* Phi-3

### Local Capabilities

| Feature               | Local Support |
| --------------------- | ------------- |
| PDF Processing        | ✅             |
| Audio Processing      | ✅             |
| Video Processing      | ✅             |
| Timestamp Navigation  | ✅             |
| Whisper Transcription | ✅             |
| Ollama Local LLMs     | ✅             |

---

## 🔹 Public Cloud Deployment

For lightweight and scalable public deployment, the application integrates:

* Groq API
* Cloud-hosted LLM inference

The public deployment is optimized specifically for:

* PDF processing
* AI summarization
* Contextual question answering

This architecture was selected to:

* simplify cloud deployment
* improve response speed
* reduce infrastructure complexity
* avoid hosting large multimedia AI pipelines in production
* optimize deployment costs on public cloud platforms

### Public Deployment Capabilities

| Feature                | Public Support |
| ---------------------- | -------------- |
| PDF Processing         | ✅              |
| AI Summarization       | ✅              |
| Question Answering     | ✅              |
| Audio Processing       | ❌              |
| Video Processing       | ❌              |
| Whisper Transcription  | ❌              |
| Local Ollama Inference | ❌              |

---

## 🔹 Hybrid AI Workflow

```text
Local Development:
React → Spring Boot → Ollama + Whisper + FFmpeg

Public Deployment:
React → Spring Boot → Groq API
```

---

## 🔹 Engineering Advantages

The hybrid architecture demonstrates:

* local LLM integration expertise
* scalable AI application design
* production deployment optimization
* multimedia AI pipeline engineering
* cloud deployment adaptability
* real-world deployment tradeoff handling

This approach enables:

* advanced multimedia experimentation locally
* lightweight cloud deployment for public accessibility

---

# 🛠️ Technology Stack

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* REST APIs
* Maven

## Frontend

* React
* Vite
* Axios
* CSS3
* React Hot Toast

## AI & Multimedia

* Spring AI
* Ollama
* Groq API
* Whisper AI
* FFmpeg
* Local LLM Integration

## Database

* PostgreSQL
* Neon PostgreSQL

## Deployment & Hosting

* Render
* Neon
* GitHub

---

# 🏗️ System Architecture

```text
React Frontend
       ↓
Spring Boot REST APIs
       ↓
AI Processing Layer
(Ollama + Groq + Whisper + FFmpeg)
       ↓
PostgreSQL Database (Neon)
```

---

# ☁️ Deployment Architecture

The application is fully deployed using cloud-native services.

## Frontend Deployment

* Hosted on Render Static Hosting
* Global CDN delivery
* Auto deployment from GitHub

## Backend Deployment

* Hosted on Render Docker Environment
* Spring Boot production deployment
* REST API infrastructure

## Database Deployment

* PostgreSQL hosted on Neon
* Cloud-native managed database
* Persistent production data storage

---

# 🔄 CI/CD Workflow

The project uses automatic deployment through GitHub integration.

```text
Code Changes
     ↓
Git Push
     ↓
Render Auto Deploy
     ↓
Production Application Updated
```

Every push to the connected GitHub branch automatically triggers:

* frontend rebuild
* backend redeployment
* production synchronization

This demonstrates:

* CI/CD workflow understanding
* cloud deployment experience
* production infrastructure management
* automated deployment pipeline integration

---

# 📸 Application Modules

## Home Interface

* Modern AI SaaS-inspired responsive UI
* Interactive multimedia upload support
* Feature-oriented landing page

## PDF Intelligence Module

* AI-generated summaries
* Contextual semantic question answering
* Document understanding pipeline

## Audio & Video Processing Module

* Multimedia transcription generation
* Timestamp-aware playback
* AI-assisted multimedia interaction

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/Suvarna6612/ai-multimedia-knowledge-assistant.git
```

---

## 2️⃣ Backend Setup

```bash
cd backend
```

### Configure Environment Variables

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=

GROQ_API_KEY=

OLLAMA_BASE_URL=http://localhost:11434
```

### Run Backend Service

```bash
mvn spring-boot:run
```

Backend Server:

```text
http://localhost:8080
```

---

## 3️⃣ Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Frontend Server:

```text
http://localhost:5173
```

---

# 📂 Supported File Formats

## Documents

* PDF (.pdf)

## Audio

* MP3 (.mp3)
* WAV (.wav)
* MPEG (.mpeg)
* AAC (.aac)
* M4A (.m4a)

## Video

* MP4 (.mp4)
* MOV (.mov)
* AVI (.avi)
* MKV (.mkv)

---

# 🚀 Future Enhancements

* Retrieval-Augmented Generation (RAG)
* Vector Database Integration
* Real-Time Streaming Transcription
* Multi-Language AI Support
* Authentication & Authorization
* Cloud-Based File Storage
* Semantic Multimedia Search
* Persistent AI Conversation History
* Streaming AI Responses

---

# 👨‍💻 Author

Developed by Suvarna Chandarlapati

---

# ⭐ Support

If you found this project useful, consider giving the repository a star ⭐
