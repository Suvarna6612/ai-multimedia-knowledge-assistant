import { useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";

function ChatBox({ fileId }) {

  const [question, setQuestion] = useState("");

  const [answer, setAnswer] = useState("");

  const [timestamp, setTimestamp] = useState(null);

  const [loading, setLoading] = useState(false);

  const askQuestion = async () => {

    if (!question.trim()) return;

    try {

      setLoading(true);

      toast.loading("AI is thinking...", {
        id: "question",
      });

      const response = await axios.post(
        `${import.meta.env.VITE_API_BASE_URL}/api/questions/ask`,
        {
          question: question,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      setAnswer(response.data.data.answer);

      setTimestamp(response.data.data.timestamp);

      toast.success("Answer generated!", {
        id: "question",
      });

    } catch (error) {

      console.error(error);

      toast.error("Failed to get answer.", {
        id: "question",
      });

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
    <div className="chat-card">

      <h2>🤖 AI Assistant</h2>

      <textarea
        rows="5"
        className="chat-input"
        placeholder="Ask something about the uploaded file..."
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
      />

      <button
        className="upload-btn"
        onClick={askQuestion}
        disabled={loading}
      >
        {loading ? (
          <span className="loader"></span>
        ) : (
          "Ask Question"
        )}
      </button>

      {answer && (

        <div className="answer-box">

          <h3>✨ AI Answer</h3>

          <p>{answer}</p>

          {timestamp !== null && (

            <>
              <p className="timestamp">
                ⏱ Relevant Timestamp: {timestamp.toFixed(1)} seconds
              </p>

              <button
                className="upload-btn"
                onClick={playFromTimestamp}
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