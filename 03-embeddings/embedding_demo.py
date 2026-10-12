from sentence_transformers import SentenceTransformer

model = SentenceTransformer('all-MiniLM-L6-v2')

sentences = [
    "Java Developer",
    "Spring Boot Engineer",
    "Python Programmer"
]

embeddings = model.encode(sentences)

for i, embedding in enumerate(embeddings):
    print(f"\nSentence: {sentences[i]}")
    print(f"Vector Length: {len(embedding)}")
    print(embedding[:10])