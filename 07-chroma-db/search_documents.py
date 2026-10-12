import chromadb

# Connect to existing database
client = chromadb.PersistentClient(path="./chromadb_data")

# Open collection
collection = client.get_collection(
    name="leave_policy"
)

question = input("Question: ")

results = collection.query(
    query_texts=[question],
    n_results=1
)

print("\nBest Match:")
print(results["documents"][0][0])