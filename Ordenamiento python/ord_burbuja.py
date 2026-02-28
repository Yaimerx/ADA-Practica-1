def ordenamiento_burbuja(arr):

    tam = len(arr)
    inter = True

    while inter:
        inter = False       
        for i in range(tam - 1):
            if arr[i] > arr[i+1]:          
                arr[i], arr[i+1] = arr[i+1], arr[i] 
                inter = True      
    return arr

arr = [42, 7, 89, 15, 63, 2, 31, 55, 98, 12]
print(f"Arreglo original: {arr}")
arr_ordenado = ordenamiento_burbuja(arr) 
print(f"Arreglo ordenado: {arr_ordenado}")