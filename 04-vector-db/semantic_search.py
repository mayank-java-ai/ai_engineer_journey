from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

model = SentenceTransformer("all-MiniLM-L6-v2")

documents = [
    "Paternity Leave : 15 Days",
    "Maternity Leave : 26 Weeks",
    "Work From Home : 2 Days Per Week"
]
#document_embeddings storing in RAM memory
document_embeddings = model.encode(documents)

question = input("Ask Question : ")

question_embedding = model.encode([question])

similarities = cosine_similarity(
    [question_embedding[0]],
    document_embeddings
)

best_match_index = similarities.argmax()

print("\nMost Relevant Result:")
print(documents[best_match_index])

print("\nSimilarity Score:")
print(similarities[0][best_match_index])