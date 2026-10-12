import ollama

from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

# Embedding model
model = SentenceTransformer("all-MiniLM-L6-v2")

# Read document
with open("policy.txt", "r") as file:
    document = file.read()

# Chunking
chunks = document.split("\n\n")

# Create embeddings
chunk_embeddings = model.encode(chunks)

# User question
question = input("Ask Question: ")

# Question embedding
question_embedding = model.encode([question])

# Similarity search
scores = cosine_similarity(
    [question_embedding[0]],
    chunk_embeddings
)

best_chunk_index = scores.argmax()

best_chunk = chunks[best_chunk_index]

print("\nRetrieved Chunk:")
print(best_chunk)

# Build prompt
prompt = f"""
Answer the question only using the context below.

Context:
{best_chunk}

Question:
{question}
"""

# LLM call
response = ollama.chat(
    model="llama3.2",
    messages=[
        {
            "role": "user",
            "content": prompt
        }
    ]
)

print("\nFinal Answer:")
print(response["message"]["content"])