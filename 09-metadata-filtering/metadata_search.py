import chromadb

client = chromadb.PersistentClient(
    path="./metadata_db"
)

collection = client.get_collection(
    name="company_docs"
)

results = collection.query(
    query_texts=["allowance"],
    n_results=3,
    where={
        "department": "travel"
    }
)

print(results["documents"])