function MessageBubble({ text, sender }) {
  return (
    <div
      style={{
        background:
          sender === "user"
            ? "#DCF8C6"
            : "#F1F1F1",
        padding: "10px",
        margin: "10px",
        borderRadius: "10px"
      }}
    >
      {text}
    </div>
  );
}

export default MessageBubble;