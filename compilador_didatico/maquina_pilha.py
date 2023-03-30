with open('codigo_gerado.txt', 'r') as file:
    codigo = file.read().split("\n")

pilha = []

for instrucao in codigo:
    if "PUSH" in instrucao:
        pilha.append(int(instrucao.split(" ")[1]))
    
    if instrucao == "SUB":
        R1, R2 = pilha.pop(), pilha.pop()
        pilha.append(R2 - R1) 
    if instrucao == "SUM":
        R1, R2 = pilha.pop(), pilha.pop()
        pilha.append(R2 + R1) 
    if instrucao == "DIV":
        R1, R2 = pilha.pop(), pilha.pop()
        pilha.append(R2 / R1) 
    if instrucao == "MULT":
        R1, R2 = pilha.pop(), pilha.pop()
        pilha.append(R2 * R1) 

print(pilha.pop())