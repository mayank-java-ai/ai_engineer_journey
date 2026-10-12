import chromadb

client = chromadb.PersistentClient(
    path="./metadata_db"
)

collection = client.get_or_create_collection(
    name="company_docs"
)

collection.add(
    documents=[
        "Travel Allowance : 5000 INR",
        "Paternity Leave : 15 Days",
        "Work From Home : 2 Days"
    ],
    metadatas=[
        {"department": "travel"},
        {"department": "hr"},
        {"department": "hr"}
    ],
    ids=["1", "2", "3"]
)

print("Stored")