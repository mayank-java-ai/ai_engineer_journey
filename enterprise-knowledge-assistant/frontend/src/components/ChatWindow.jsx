import MessageBubble from "./MessageBubble";

function ChatWindow({
  messages
}) {

  return (
    <div>

      {
        messages.map(
          (
            message,
            index
          ) => (

            <MessageBubble
              key={index}
              text={message.text}
              sender={
                message.sender
              }
            />

          )
        )
      }

    </div>
  );
}

export default ChatWindow;