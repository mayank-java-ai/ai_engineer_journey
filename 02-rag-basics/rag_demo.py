with open("leave-policy.txt", "r") as file:
    document = file.read()

print("Document Loaded Successfully\n")

question = input("Ask Question: ")

if "paternity" in question.lower():
    print("\nAnswer:")
    print("15 Days")

elif "maternity" in question.lower():
    print("\nAnswer:")
    print("26 Weeks")

elif "annual" in question.lower():
    print("\nAnswer:")
    print("30 Days")

else:
    print("\nAnswer not found.")


# Question
# ↓
# IF ELSE
# ↓
# Answer