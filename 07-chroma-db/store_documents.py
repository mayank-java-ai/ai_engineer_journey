import chromadb

# Create persistent database
client = chromadb.PersistentClient(path="./chromadb_data")

# Create collection
collection = client.get_or_create_collection(
    name="leave_policy"
)

# Store documents
collection.add(
    documents=[
        "Paternity Leave : 15 Days",
        "Maternity Leave : 26 Weeks",
        "Work From Home : 2 Days Per Week"
    ],
    ids=[
        "1",
        "2",
        "3"
    ]
)

print("Documents stored successfully!")