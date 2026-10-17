import { useState } from "react";

import api from "../services/api";

import UploadForm from "../components/UploadForm";

import ChatWindow from "../components/ChatWindow";

function ChatPage() {
  const [question, setQuestion] = useState("");

  const [messages, setMessages] = useState([]);

  const sendMessage = async () => {
    if (!question.trim()) {
      return;
    }

    const response = await api.post("/api/chat", {
      question,
    });

    setMessages([
      ...messages,

      {
        sender: "user",
        text: question,
      },

      {
        sender: "ai",
        text: response.data.answer,
      },
    ]);

    setQuestion("");
  };

  return (
    <div
      style={{
        width: "80%",
        margin: "auto",
        marginTop: "20px",
      }}
    >
      <h1>Enterprise Knowledge Assistant</h1>

      <UploadForm />

      <hr />

      <ChatWindow messages={messages} />

      <input
        value={question}
        onChange={(e) => setQuestion(e.target.value)}
        placeholder="Ask something..."
        style={{
          width: "70%",
          padding: "10px",
        }}
      />

      <button onClick={sendMessage}>Send</button>
    </div>
  );
}

export default ChatPage;
