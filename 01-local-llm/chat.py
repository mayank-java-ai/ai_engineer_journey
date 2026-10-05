import ollama
response = ollama.chat(
model='llama3.2',
messages=[
{
'role': 'user',
'content': 'What is Spring Boot?'
}
]
)
print(response['message']['content'])


# User
# ↓
# LLM
# ↓
# Answer
