import { useState } from "react";
import axios from "axios";

function ChatBox({ fileId }) {
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [timestamp, setTimestamp] = useState(null);
  const [loading, setLoading] = useState(false);

  const askQuestion = async () => {
    if (!question.trim()) return;

    try {
      setLoading(true);

      const response = await axios.post(
        "https://ai-multimedia-knowledge-assistant.onrender.com/api/questions/ask",
        {
          fileId,
          question,
        }
      );

      // Backend response structure:
      // {
      //   success: true,
      //   data: {
      //     answer: "...",
      //     timestamp: 4.6
      //   }
      // }

      setAnswer(response.data.data.answer);
      setTimestamp(response.data.data.timestamp);
    } catch (error) {
      console.error("Question failed:", error);
      alert("Failed to get answer.");
    } finally {
      setLoading(false);
    }
  };

  const playFromTimestamp = () => {
    const mediaPlayer = document.getElementById("media-player");

    if (mediaPlayer && timestamp !== null) {
      mediaPlayer.currentTime = timestamp;
      mediaPlayer.play();
    }
  };

  return (
    <div
      style={{
        marginTop: "20px",
        padding: "20px",
        border: "1px solid #ddd",
        borderRadius: "10px",
      }}
    >
      <h2>Ask Questions</h2>

      <textarea
        rows="4"
        style={{ width: "100%", marginBottom: "10px" }}
        placeholder="Ask something about the document..."
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
      />

      <button onClick={askQuestion} disabled={loading}>
        {loading ? "Thinking..." : "Ask Question"}
      </button>

      {answer && (
        <div style={{ marginTop: "20px" }}>
          <h3>Answer</h3>
          <p style={{ whiteSpace: "pre-wrap" }}>{answer}</p>

          {timestamp !== null && (
            <>
              <p
                style={{
                  marginTop: "15px",
                  fontWeight: "bold",
                  fontSize: "20px",
                }}
              >
                ⏱ Relevant Timestamp: {timestamp.toFixed(1)} seconds
              </p>

              <button
                onClick={playFromTimestamp}
                style={{
                  marginTop: "10px",
                  padding: "10px 20px",
                  backgroundColor: "#28a745",
                  color: "white",
                  border: "none",
                  borderRadius: "6px",
                  cursor: "pointer",
                  fontSize: "16px",
                }}
              >
                ▶ Play from Timestamp
              </button>
            </>
          )}
        </div>
      )}
    </div>
  );
}

export default ChatBox;