import ollama

messages = []

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
        model="llama3.2",
        messages=messages
    )

    answer = response["message"]["content"]

    print("\nAgent :")
    print(answer)

    messages.append(
        {
            "role": "assistant",
            "content": answer
        }
    )