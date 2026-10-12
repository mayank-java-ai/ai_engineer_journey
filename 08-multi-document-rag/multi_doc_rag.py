import chromadb
import ollama

client = chromadb.PersistentClient(
    path="./multi_doc_db"
)

collection = client.get_collection(
    name="company_policies"
)

question = input("Question : ")

results = collection.query(
    query_texts=[question],
    n_results=3
)

context = "\n".join(
    results["documents"][0]
)

prompt = f"""
Answer only from the context below.

Context:
{context}

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

print("\nFinal Answer:\n")
print(response["message"]["content"])