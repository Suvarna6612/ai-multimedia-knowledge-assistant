import { useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";

function FileUpload({ onUploadSuccess }) {

  const [selectedFile, setSelectedFile] = useState(null);

  const [loading, setLoading] = useState(false);

  const handleFileChange = (e) => {
    setSelectedFile(e.target.files[0]);
  };

  const getFileBadge = () => {

    if (!selectedFile) return "";

    const fileType = selectedFile.type;

    if (fileType.includes("pdf")) {
      return "📄 PDF File Selected";
    }

    if (fileType.includes("video")) {
      return "🎥 Video File Selected";
    }

    if (fileType.includes("audio")) {
      return "🎧 Audio File Selected";
    }

    return "📁 File Selected";
  };

  const handleUpload = async () => {

    if (!selectedFile) {
      toast.error("Please select a file first.");
      return;
    }

    const formData = new FormData();

    formData.append("file", selectedFile);

    try {

      setLoading(true);

      toast.loading("AI is processing your file...", {
        id: "upload",
      });

      const response = await axios.post(
        `${import.meta.env.VITE_API_BASE_URL}/api/files/upload`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      onUploadSuccess(response.data);

      toast.success("File uploaded successfully!", {
        id: "upload",
      });

    } catch (error) {

      console.error(error);

      toast.error("Upload failed.", {
        id: "upload",
      });

    } finally {
      setLoading(false);
    }
  };

  return (
    <div>

      <input
        type="file"
        className="upload-input"
        accept=".pdf,.mp3,.wav,.mpeg,.mp4,.m4a,.aac,.mov"
        onChange={handleFileChange}
      />

      {selectedFile && (
        <p className="file-badge">
          {getFileBadge()}
        </p>
      )}

      <button
        className="upload-btn"
        onClick={handleUpload}
        disabled={loading}
      >

        {loading ? (
          <span className="loader"></span>
        ) : (
          "Upload File"
        )}

      </button>

    </div>
  );
}

export default FileUpload;