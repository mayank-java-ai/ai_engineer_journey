import ollama

messages = []

print("AI Assistant Started")
print("Type exit to quit")

while True:

    question = input("\nYou : ")

    if question.lower() == "exit":
        break

    messages.append(
        {
            "role": "user",
            "content": question
        }
    )

    response = ollama.chat(
        model='llama3.2',
        messages=messages
    )

    answer = response['message']['content']

    print("\nAI:")
    print(answer)

    messages.append(
        {
            "role": "assistant",
            "content": answer
        }
    )


# Conversation History
# ↓
# LLM
# ↓
# Context Aware Response