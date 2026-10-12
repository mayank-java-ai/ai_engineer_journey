import ollama


def research_agent(topic):

    response = ollama.chat(
        model="llama3.2",
        messages=[
            {
                "role": "user",
                "content": f"Provide information about {topic}"
            }
        ]
    )

    return response["message"]["content"]


def writer_agent(research):

    response = ollama.chat(
        model="llama3.2",
        messages=[
            {
                "role": "user",
                "content": f"Write a report using:\n{research}"
            }
        ]
    )

    return response["message"]["content"]


def reviewer_agent(report):

    response = ollama.chat(
        model="llama3.2",
        messages=[
            {
                "role": "user",
                "content": f"""
Review the report.
Improve clarity.
Improve structure.
Return final version.

Report:
{report}
"""
            }
        ]
    )

    return response["message"]["content"]


topic = input("Topic: ")

research = research_agent(topic)

print("\n=== RESEARCH ===")
print(research)

draft = writer_agent(research)

print("\n=== WRITER ===")
print(draft)

final_report = reviewer_agent(draft)

print("\n=== REVIEWER ===")
print(final_report)