from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

model = SentenceTransformer("all-MiniLM-L6-v2")

chunks = [
    "Annual Leave : 30 Days",
    "Paternity Leave : 15 Days",
    "Maternity Leave : 26 Weeks",
    "Work From Home : 2 Days Per Week"
]

chunk_embeddings = model.encode(chunks)

question = input("Question : ")

question_embedding = model.encode([question])

scores = cosine_similarity(
    [question_embedding[0]],
    chunk_embeddings
)

best_chunk = scores.argmax()

print("\nBest Chunk:")
print(chunks[best_chunk])

print("\nScore:")
print(scores[0][best_chunk])