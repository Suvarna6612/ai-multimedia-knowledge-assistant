function SummaryCard({ summary }) {
  if (!summary) {
    return null;
  }

  return (
    <div
      style={{
        marginTop: "20px",
        padding: "20px",
        border: "1px solid #ddd",
        borderRadius: "10px",
        backgroundColor: "#f9f9f9",
      }}
    >
      <h2>Document Summary</h2>
      <p style={{ whiteSpace: "pre-wrap" }}>{summary}</p>
    </div>
  );
}

export default SummaryCard;