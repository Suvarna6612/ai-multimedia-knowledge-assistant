import { useState } from "react";
import FileUpload from "./components/FileUpload";
import ChatBox from "./components/ChatBox";
import "./App.css";

function App() {
  const [uploadData, setUploadData] = useState(null);

  return (
    <div className="App">
      <h1>AI Multimedia Knowledge Assistant</h1>

      {/* File Upload */}
      <FileUpload onUploadSuccess={setUploadData} />

      {uploadData && (
        <>
          {/* Document Summary */}
          <div
            style={{
              marginTop: "20px",
              padding: "30px",
              border: "1px solid #ddd",
              borderRadius: "12px",
            }}
          >
            <h2>Document Summary</h2>
            <p
              style={{
                whiteSpace: "pre-wrap",
                lineHeight: "1.8",
                fontSize: "18px",
              }}
            >
              {uploadData.summary}
            </p>
          </div>

          {/* Media Player for Audio and Video */}
          {(uploadData.fileType?.startsWith("audio") ||
            uploadData.fileType?.startsWith("video")) && (
            <div
              style={{
                marginTop: "20px",
                padding: "30px",
                border: "1px solid #ddd",
                borderRadius: "12px",
              }}
            >
              <h2>Media Player</h2>

              {uploadData.fileType.startsWith("audio") ? (
                <audio
                  id="media-player"
                  controls
                  style={{ width: "100%", marginTop: "20px" }}
                  src={`http://localhost:8080/uploads/${uploadData.fileName}`}
                />
              ) : (
                <video
                  id="media-player"
                  controls
                  style={{
                    width: "100%",
                    maxHeight: "500px",
                    marginTop: "20px",
                    borderRadius: "10px",
                  }}
                  src={`http://localhost:8080/uploads/${uploadData.fileName}`}
                />
              )}
            </div>
          )}

          {/* Chat Section */}
          <ChatBox fileId={uploadData.fileId} />
        </>
      )}
    </div>
  );
}

export default App;