from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

model = SentenceTransformer('all-MiniLM-L6-v2')

text1 = "Java Developer"
text2 = "Spring Boot Engineer"
text3 = "Java Programmer"

emb1 = model.encode([text1])
emb2 = model.encode([text2])
emb3 = model.encode([text3])

print(
    "Java vs Spring:",
    cosine_similarity(emb1, emb2)[0][0]
)

print(
    "Java vs Cricket:",
    cosine_similarity(emb1, emb3)[0][0]
)