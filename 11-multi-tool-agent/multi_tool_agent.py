import datetime
import chromadb
import ollama


def time_tool():
    return f"Current Time: {datetime.datetime.now()}"


def calculator_tool(expression):
    try:
        return str(eval(expression))
    except:
        return "Invalid Expression"


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

    # Time Tool

    if "time" in question.lower():

        print("\n[Agent]")
        print("Selected Tool: Time Tool")

        print(time_tool())

    # Calculator Tool

    elif any(op in question for op in ["+", "-", "*", "/"]):

        print("\n[Agent]")
        print("Selected Tool: Calculator Tool")

        print(
            calculator_tool(question)
        )

    # RAG Tool

    else:

        print("\n[Agent]")
        print("Selected Tool: RAG Tool")

        context = rag_tool(question)

        prompt = f"""
        Answer only using the context below.

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