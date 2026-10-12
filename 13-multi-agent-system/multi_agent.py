import ollama


def research_agent(topic):

    prompt = f"""
    Research the topic.

    Topic:
    {topic}
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

    return response["message"]["content"]


def writer_agent(research):

    prompt = f"""
    Create a concise report.

    Information:

    {research}
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

    return response["message"]["content"]


topic = input("Topic: ")

research = research_agent(topic)

print("\n=== RESEARCH AGENT ===\n")
print(research)

report = writer_agent(research)

print("\n=== WRITER AGENT ===\n")
print(report)