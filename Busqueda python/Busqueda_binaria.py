
arr = [42, 7, 89, 15, 63, 2, 31, 55, 98, 12]
numero_a_buscar = int(input("Ingrese el número que desea buscar: "))

def ord_quick(arr):
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[len(arr) // 2]
        izq = [x for x in arr if x < pivot]
        centr = [x for x in arr if x == pivot]
        der = [x for x in arr if x > pivot]
        return ord_quick(izq) + centr + ord_quick(der)
    
resultado = ord_quick(arr)
print(f"Arreglo: {arr}")
print(f"Arreglo ordenado: {resultado}")


def busqueda_binaria(arreglo, objetivo):
    inicio = 0
    fin = len(arreglo) - 1
    
    while inicio <= fin:
  
        medio = (inicio + fin) // 2
        
        if arreglo[medio] == objetivo:
            return medio

        elif arreglo[medio] < objetivo:
            inicio = medio + 1
       
        else:
            fin = medio - 1
        
    return -1

resultado = busqueda_binaria(resultado, numero_a_buscar)


if resultado != -1:
    print(f"¡Número {numero_a_buscar} encontrado en el índice {resultado+1}!")
else:
    print("El número no existe en el arreglo.")