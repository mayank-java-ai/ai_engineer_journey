# AI Engineer Journey

Journey from Java Spring Boot Developer to AI Engineer.

## Completed

### 01 Local LLM

- Ollama Setup
- Llama 3.2
- Basic LLM Call
- Chatbot
- Chatbot With Memory

### 02 RAG Basics
 
- Built Rule Based Retrieval System
- Created Leave Policy Knowledge Base
- Implemented Context Injection with Llama 3.2
- Understood Difference Between Retrieval and Generation

03 Embeddings
 - Generated text embeddings using Sentence Transformers
 - Compared semantic similarity using cosine similarity
 - Understood vector representations of text
 - Learned foundation concepts behind semantic search and RAG
Files
 - embedding_demo.py
 - similarity_demo.py

### 04 Vector Database
 
- Built Semantic Search Engine
- Generated Document Embeddings
- Compared Query and Document Vectors
- Retrieved Most Relevant Result Using Cosine Similarity
- Understood Foundation of Vector Databases


### 06 Real RAG
 - Built End-to-End RAG Pipeline
 - Document Chunking
 - Embedding Generation
 - Semantic Retrieval
 - Context Injection
 - LLM Based Answer Generation
 - Pipeline:

Document → Chunking → Embeddings → Similarity Search → Context Retrieval → LLM → Answer

Plain Text
policy.txt
↓
Chunking
↓
Embeddings
↓
Question Embedding
↓
Cosine Similarity
↓
Best Chunk Found
↓
Prompt Create
↓
Llama 3.2
↓
Final Answer



Chalo line-by-line dekhte hain.

Step 1: Imports

import ollama
 
from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

Kya ho raha hai?
Ollama

import ollama


Use hoga:

Plain Text
Final Answer Generate Karne Ke Liye
Show more lines

Yaani Llama 3.2.

SentenceTransformer

SentenceTransformer
Use hoga:
PlainText
Embeddings Banane Ke Liye

LLM nahi hai.

Cosine Similarity
cosine_similarity
Use hoga:
Plain Text
Best Matching Chunk Dhoondhne Ke Liye

Step 2: Embedding Model Load

model = SentenceTransformer("all-MiniLM-L6-v2")


Ye model:

Plain Text
Text
↓
Vector

convert karta hai.

Example:

Plain Text
Father Leave

↓

Plain Text
[0.23, 0.72, 0.11 ...]

Step 3: Read Document

with open("policy.txt", "r") as file:
document = file.read()

Ab policy.txt read ho gayi.

Example:

Plain Text
Annual Leave : 30 Days
 
Paternity Leave : 15 Days
 
Maternity Leave : 26 Weeks

Step 4: Chunking

chunks = document.split("\n\n")

Document ko tod diya.

Result:

Python
[
"Annual Leave : 30 Days",
 
"Paternity Leave : 15 Days",
 
"Maternity Leave : 26 Weeks",
 
"Work From Home : 2 Days"
]

Step 5: Create Embeddings

chunk_embeddings = model.encode(chunks)

Ab har chunk vector ban gaya.

Example:

Plain Text
"Paternity Leave : 15 Days"
 
↓
 
[0.23, 0.56, 0.91...]
Show more lines
Step 6: User Question
Python
question = input("Ask Question: ")
Show more lines

User:

Plain Text
How much father leave is allowed?
Show more lines
Step 7: Question Embedding
Python
question_embedding = model.encode([question])
Show more lines

Ab question bhi vector ban gaya.

Plain Text
How much father leave is allowed?
 
↓
 
[0.21, 0.59, 0.87...]
Show more lines
Step 8: Similarity Search
Python
scores = cosine_similarity(
[question_embedding[0]],
chunk_embeddings
)


Ab comparison ho raha hai.

Plain Text
Question Vector
Show more lines

vs

Plain Text
Annual Leave Vector
 
Paternity Leave Vector
 
Maternity Leave Vector
 
WFH Vector
Show more lines

Example:

Plain Text
Annual Leave → 0.11
 
Paternity Leave → 0.79
 
Maternity Leave → 0.28
 
WFH → 0.05

Step 9: Best Chunk
best_chunk_index = scores.argmax()
Show more lines

Highest score:

Plain Text
0.79
Show more lines

Mil gaya.

Python
best_chunk = chunks[best_chunk_index]


Result:

Plain Text
Paternity Leave : 15 Days
Show more lines
Step 10: Show Retrieved Chunk
Python
print(best_chunk)
Show more lines

Output:

Plain Text
Retrieved Chunk:
 
Paternity Leave : 15 Days
Show more lines
IMPORTANT

Yahin tak:

Plain Text
LLM use nahi hua
Show more lines

Yahan tak sirf:

✅ Embeddings

✅ Similarity Search

✅ Retrieval

hua hai.

Step 11: Prompt Create
Python
prompt = f"""
Answer the question only using the context below.
 
Context:
{best_chunk}
 
Question:
{question}
"""
Show more lines

Prompt ban gaya:

Plain Text
Answer the question only using the context below.
 
Context:
Paternity Leave : 15 Days
 
Question:
How much father leave is allowed?
Show more lines
Step 12: LLM Call
Python
response = ollama.chat(
Show more lines

💥

Ab finally Llama use ho raha hai.

Yahan:

Plain Text
Prompt
↓
Llama 3.2
↓

Step 13: Final Answer

Llama bol sakta hai:

Plain Text
Employees are entitled to 15 days of paternity leave.
Show more lines
Complete Flow
Plain Text
policy.txt
↓
Chunking
↓
Embeddings
↓
Question Embedding
↓
Cosine Similarity
↓
Best Chunk Found
↓
Prompt Create
↓
Llama 3.2
↓
Final Answer

Sabse Important Interview Question
Retrieval kahan ho raha hai?

Answer:

Python
question_embedding
+
chunk_embeddings
+
cosine_similarity
Show more lines

Yahi Retrieval hai.

Generation kahan ho raha hai?

Answer:

Python
ollama.chat(...)
Show more lines

Yahi Generation hai.

Ek Line Me

real_rag.py me:

Plain Text
SentenceTransformer
=
Retriever
 
Llama 3.2
=
Generator
Show more lines

Aur isi combination ko industry me bolte hain:

Plain Text
RAG
=
Retrieval Augmented Generation

## Upcoming

- Agents
- MCP
- LangChain4j
- Spring Boot AI