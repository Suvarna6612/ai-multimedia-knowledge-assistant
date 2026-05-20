function MessageBubble({ role, text }) {
  return (
    <div
      style={{
        margin: "10px 0",
        padding: "10px",
        borderRadius: "8px",
        backgroundColor: role === "user" ? "#e3f2fd" : "#f5f5f5",
      }}
    >
      <strong>{role === "user" ? "You" : "AI"}:</strong>
      <p style={{ margin: "5px 0 0", whiteSpace: "pre-wrap" }}>{text}</p>
    </div>
  );
}

export default MessageBubble;