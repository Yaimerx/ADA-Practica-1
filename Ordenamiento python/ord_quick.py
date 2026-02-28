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
    
resultado = ord_quick(arr)
print("Arreglo ordenado:", resultado)
