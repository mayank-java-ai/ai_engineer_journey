import chromadb
import ollama


def rag_tool(question):

    client = chromadb.PersistentClient(
        path="../08-multi-document-rag/multi_doc_db"
    )

    collection = client.get_collection(
        name="company_policies"
    )

    results = collection.query(
        query_texts=[question],
        n_results=3
    )

    return "\n".join(
        results["documents"][0]
    )


while True:

    question = input("\nQuestion: ")

    if question.lower() == "exit":
        break

    print("\n[Agent Thinking...]")

    context = rag_tool(question)

    print("\n[Tool Selected]")
    print("RAG Tool")

    prompt = f"""
    Answer using only the context below.

    Context:
    {context}

    Question:
    {question}
    """

    response = ollama.chat(
        model="llama3.2",
        messages=[
            {
                "role": "user",
                "content": prompt
            }
        ]
    )

    print("\nAnswer:")
    print(
        response["message"]["content"]
    )