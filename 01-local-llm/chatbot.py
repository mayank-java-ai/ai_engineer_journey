import ollama

print("AI Assistant Started")
print("Type 'exit' to quit")

while True:

    question = input("\nYou : ")

    if question.lower() == "exit":
        print("Goodbye!")
        break

    response = ollama.chat(
        model='llama3.2',
        messages=[
            {
                'role': 'user',
                'content': question
            }
        ]
    )

    print("\nAI :")
    print(response['message']['content'])

# User
# ↓
# LLM
# ↓
# Answer
# ↓
# User
# ↓
# LLM