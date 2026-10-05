import ollama

with open("leave-policy.txt", "r") as file:
    document = file.read()

question = input("Ask Question: ")

prompt = f"""
Use the following document to answer the question.

Document:
{document}

Question:
{question}
"""

response = ollama.chat(
    model="llama3.2",
    messages=[
        {
            "role": "user",
            "content": prompt
        }
    ]
)

print("\nAnswer:")
print(response["message"]["content"])

# Document
# +
# Question
# ↓
# LLM
# ↓
# Answer