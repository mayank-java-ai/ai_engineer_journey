import api from "../services/api";

function UploadForm() {
  const uploadFile = async (event) => {
    const formData = new FormData();

    for (let file of event.target.files) {
      formData.append("files", file);
    }

    await api.post("/api/document/upload", formData);
  };

  return <input type="file" multiple onChange={uploadFile} />;
}

export default UploadForm;
