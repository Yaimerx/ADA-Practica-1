import math


arr = [42, 7, 89, 15, 63, 2, 31, 55, 98, 12]


def ord_quick(arr):
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[len(arr) // 2]
        izq = [x for x in arr if x < pivot]
        centr = [x for x in arr if x == pivot]
        der = [x for x in arr if x > pivot]
        return ord_quick(izq) + centr + ord_quick(der)


arreglo_ordenado = ord_quick(arr)
print(f"Arreglo ordenado: {arreglo_ordenado}")


def busqueda_jump(arr, obj):
    n = len(arr)
    salto = int(math.sqrt(n))
    anter = 0
    
    while arr[min(salto, n) - 1] < obj:
        anter = salto
        salto += int(math.sqrt(n))
        if anter >= n:
            return -1
    
 
    while arr[anter] < obj:
        anter += 1
        

        if anter == min(salto, n):
            return -1
            

    if arr[anter] == obj:
        return anter
    
    return -1



numero_objetivo = int(input("\n¿Qué número deseas buscar en el arreglo?: "))

indice_encontrado = busqueda_jump(arreglo_ordenado, numero_objetivo)

if indice_encontrado != -1:
        print(f"¡Éxito! El número {numero_objetivo} está en la posición {indice_encontrado+1} del arreglo ordenado.")
else:
        print(f"Lo siento, el número {numero_objetivo} no se encuentra en la lista.")