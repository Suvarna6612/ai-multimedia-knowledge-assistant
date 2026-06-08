import { useState } from "react";
import axios from "axios";

function FileUpload({ onUploadSuccess }) {
  const [selectedFile, setSelectedFile] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const handleUpload = async () => {
    if (!selectedFile) {
      alert("Please select a file first.");
      return;
    }

    const formData = new FormData();
    formData.append("file", selectedFile);

    try {
      setLoading(true);

      const response = await axios.post(
                         "https://ai-multimedia-knowledge-assistant.onrender.com/api/files/upload",
                         formData,
                         {
                           headers: {
                             "Content-Type": "multipart/form-data",
                           },
                         }
                       );

      onUploadSuccess(response.data);
      alert("File uploaded successfully!");
    } catch (error) {
      console.error("Upload Error:", error);
      alert("Upload failed.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <input
        type="file"
        accept=".pdf,.mp3,.wav,.mpeg,.mp4,.m4a,.aac,.mov"
        onChange={handleFileChange}
      />

      <button onClick={handleUpload} disabled={loading}>
        {loading ? "Uploading..." : "Upload File"}
      </button>
    </div>
  );
}

export default FileUpload;