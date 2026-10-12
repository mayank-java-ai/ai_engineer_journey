import api from "../services/api";

function UploadForm() {

  const uploadFile = async (
    event
  ) => {

    const file =
      event.target.files[0];

    const formData =
      new FormData();

    formData.append(
      "file",
      file
    );

    await api.post(
      "/api/document/upload",
      formData
    );

    alert(
      "File Uploaded Successfully"
    );
  };

  return (
    <input
      type="file"
      onChange={uploadFile}
    />
  );
}

export default UploadForm;