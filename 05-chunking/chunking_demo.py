with open("policy.txt", "r") as file:
    document = file.read()

chunks = document.split("\n\n")

print("Total Chunks:", len(chunks))

for index, chunk in enumerate(chunks):
    print(f"\nChunk {index + 1}")
    print(chunk)
