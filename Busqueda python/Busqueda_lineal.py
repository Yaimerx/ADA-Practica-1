arr = [42, 17, 89, 23, 56, 71, 10, 95, 34, 68]
obj = int(input("Ingrese el numero a buscar: "))

def buscar_numero(arr, obj):
    for i in range(len(arr)):
        if arr[i] == obj:
            return i+1
    return -1

resultado = buscar_numero(arr, obj)

if resultado == -1:
    print(f"El numero {obj} no se encuentra en el arreglo.")
else:
 print(f"El numero {obj} se encuentra en la posicion: {resultado}")
 print(f"El arreglo tiene: {len(arr)} elementos.")
 