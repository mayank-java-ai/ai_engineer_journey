import chromadb

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

print("\nTop Results:\n")

for doc in results["documents"][0]:
    print(doc)