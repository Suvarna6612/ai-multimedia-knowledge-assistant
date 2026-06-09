import { useState } from "react";
import { Toaster } from "react-hot-toast";

import FileUpload from "./components/FileUpload";
import ChatBox from "./components/ChatBox";

import "./App.css";

function App() {
  const [uploadData, setUploadData] = useState(null);

  return (
    <div className="App">

      <Toaster
        position="top-right"
        toastOptions={{
          style: {
            background: "#0f172a",
            color: "#fff",
            border: "1px solid #2563eb",
          },
        }}
      />

      {/* HERO SECTION */}
      <div className="hero-section">

        <h1 className="main-title">
          AI Multimedia
          <br />
          Knowledge
          <br />
          Assistant
        </h1>

        <p className="hero-subtitle">
          Upload PDFs, audio, and video files to generate AI-powered
          summaries, contextual question answering, and timestamp-based
          multimedia playback.
        </p>

      </div>

      {/* FEATURES */}
      <div className="features-grid">

        <div className="feature-card">
          <h2>📄 PDF Intelligence</h2>

          <p>
            Extract text, generate summaries, and ask contextual
            questions from PDF documents.
          </p>
        </div>

        <div className="feature-card">
          <h2>🎥 Video Understanding</h2>

          <p>
            Upload videos and interact with AI-generated transcript
            insights and timestamp navigation.
          </p>
        </div>

        <div className="feature-card">
          <h2>🎧 Audio Transcription</h2>

          <p>
            Convert speech into searchable transcripts using
            Whisper speech-to-text models.
          </p>
        </div>

        <div className="feature-card">
          <h2>🤖 AI Question Answering</h2>

          <p>
            Ask natural language questions powered by
            Ollama local LLM integration.
          </p>
        </div>

      </div>

      {/* UPLOAD SECTION */}
      <div className="upload-section">

        <h2 className="upload-title">
          🚀 Upload Multimedia Files
        </h2>

        <p className="upload-subtitle">
          Upload PDFs, audio, or video files and interact
          with AI-powered summaries and Q&A.
        </p>

        <FileUpload onUploadSuccess={setUploadData} />

      </div>

      {/* SUMMARY */}
      {uploadData && (
        <>

          <div className="summary-card">

            <h2>📄 Document Summary</h2>

            <p className="summary-text">
              {uploadData.summary}
            </p>

          </div>

          {/* MEDIA PLAYER */}
          {(uploadData.fileType?.startsWith("audio") ||
            uploadData.fileType?.startsWith("video")) && (

            <div className="media-card">

              <h2>🎬 Media Player</h2>

              {uploadData.fileType.startsWith("audio") ? (
                <audio
                  id="media-player"
                  controls
                  src={`${import.meta.env.VITE_API_BASE_URL}/uploads/${uploadData.fileName}`}
                />
              ) : (
                <video
                  id="media-player"
                  controls
                  src={`${import.meta.env.VITE_API_BASE_URL}/uploads/${uploadData.fileName}`}
                />
              )}

            </div>
          )}

          {/* CHAT */}
          <ChatBox fileId={uploadData.fileId} />

        </>
      )}

      {/* FOOTER */}
      <footer className="footer">
        Built with Java, Spring Boot, React,
        Ollama, Whisper, FFmpeg, and PostgreSQL
      </footer>

    </div>
  );
}

export default App;