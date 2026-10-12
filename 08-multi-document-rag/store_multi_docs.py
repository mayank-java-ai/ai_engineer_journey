import chromadb

client = chromadb.PersistentClient(
    path="./multi_doc_db"
)

collection = client.get_or_create_collection(
    name="company_policies"
)

documents = [
    "Annual Leave : 30 Days",
    "Paternity Leave : 15 Days",
    "Maternity Leave : 26 Weeks",
    "Travel Allowance : 5000 INR",
    "Flight tickets can be reimbursed",
    "Hotel reimbursement is allowed",
    "Employees can work from home 2 days per week",
    "Manager approval is required"
]

ids = [str(i) for i in range(1, len(documents)+1)]

collection.add(
    documents=documents,
    ids=ids
)

print("Documents Stored Successfully")